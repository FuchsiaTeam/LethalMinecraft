package json.jayson.mixin;

import json.jayson.common.init.LMDataAttachments;
import json.jayson.common.objects.event.custom.PlayerDropItemCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Random;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity{

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /* PLAYER DROP ITEM EVENT */
    //Fabric really needs an event for that, man
    @Inject(at = @At("RETURN"), method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", cancellable = true)
    private void onItemDrop(CallbackInfoReturnable<ItemEntity> ci) {
        ItemEntity item = PlayerDropItemCallback.EVENT.invoker().drop((PlayerEntity) (Object) this, ci.getReturnValue());
        if(ci.getReturnValue() == null) {
            ci.cancel();
        } else {
            ci.setReturnValue(item);
        }
    }


    @Inject(at = @At("RETURN"), method = "tickMovement")
    private void onPlayerTick(CallbackInfo ci) {
        int stamin = this.getAttachedOrCreate(LMDataAttachments.STAMINA);
        if(isSprinting()) {
            modifyAttached(LMDataAttachments.STAMINA, stamina -> stamin - 1);
        }
        if(stamin < 1) {
            EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            LivingEntity e = (LivingEntity) (Object) this;
            entityAttributeInstance.removeModifier(e.SPRINTING_SPEED_BOOST.getId());
        }
        /* FORCE PLAYERS TO HOLD KEY */
        setSprinting(false);
    }

    @Inject(at = @At("RETURN"), method = "tick")
    private void tick(CallbackInfo ci) {
        int stamin = this.getAttachedOrCreate(LMDataAttachments.STAMINA);
        if(stamin < 1) {
            /*EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            LivingEntity e = (LivingEntity) (Object) this;
            entityAttributeInstance.removeModifier(e.SPRINTING_SPEED_BOOST.getId());*/
            getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.03f);
        } else {
            getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1f);
        }
        if(!isSprinting()) {
            if(new Random().nextInt(3) == 1) {
                modifyAttached(LMDataAttachments.STAMINA, stamina -> stamin + 1);
            }
        }
    }

}
