package json.jayson.common.objects.entity.coil_head;

import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class CoilHeadEntity extends MobEntity implements GeoEntity {

    private final AnimatableInstanceCache animatableInstanceCache = new SingletonAnimatableInstanceCache(this);


    public static final String IDLE_ANIMATION = "idle";
    public static final String SPRINGED_ANIMATION = "springed";
    private boolean seen = false;
    private boolean springed = false;

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    public CoilHeadEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder attributes() {
        return LivingEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50000d)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 50d)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10d);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new LookAroundGoal(this));
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {

        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        springed = !springed;
        return ActionResult.CONSUME;
    }

    @Override
    public void tick() {
        super.tick();
        if(!getWorld().isClient) {
            if (getWorld().getTime() % 20 == 0) {
                for (PlayerEntity players : getWorld().getEntitiesByClass(PlayerEntity.class, Box.of(getPos(), 15, 2, 15), player -> player.isCreative())) {
                    // Only checks if blocks are in the way, not if the entity is in the viewport or not
                    if (players.canSee(this)) {
                        seen = false;
                    }
                }
            }
        }

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<GeoAnimatable> state) {
        if(springed) {
            state.getController().setAnimation(RawAnimation.begin().then(SPRINGED_ANIMATION, Animation.LoopType.HOLD_ON_LAST_FRAME));
        } else {
            state.getController().setAnimation(RawAnimation.begin().then(IDLE_ANIMATION, Animation.LoopType.LOOP));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableInstanceCache;
    }
}
