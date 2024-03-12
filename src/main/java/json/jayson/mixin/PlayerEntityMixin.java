package json.jayson.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import json.jayson.common.init.LMDataAttachments;
import json.jayson.common.objects.event.custom.PlayerDropItemCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.world.World;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity{

    @Shadow public abstract boolean isCreative();

    @Shadow public abstract boolean isSpectator();

    @Shadow public abstract PlayerInventory getInventory();

    @Shadow protected abstract boolean isArmorSlot(EquipmentSlot slot);

    @Shadow public float strideDistance;

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
        if(isSprinting() && !isCreative() && !isSpectator()) {
            modifyAttached(LMDataAttachments.STAMINA, stamina -> stamin - 1);
        }
        if(stamin < 1) {
            getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1f);
        }
        if(this.jumping && !isCreative() && !isSpectator()) {
            modifyAttached(LMDataAttachments.STAMINA, stamina -> stamin - 3);
            setJumping(false);
        }

        /* FORCE PLAYERS TO HOLD KEY */
        if(!isCreative() && !isSpectator()) {
            setSprinting(false);
        }
    }

    @Inject(at = @At("RETURN"), method = "tick")
    private void tick(CallbackInfo ci) {
        int stamin = this.getAttachedOrCreate(LMDataAttachments.STAMINA);
        if(stamin < 1) {
            getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.03f);
        } else {
            getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1f);
        }
        if(!isSprinting() && !jumping) {
            if(getWorld().getTime() % 4 == 0) {
                if(stamin < 101) {
                    modifyAttached(LMDataAttachments.STAMINA, stamina -> stamin + 1);
                }
            }
        }

        /*if(getWorld().getTime() % 800 == 0) {
            System.out.println("UPDATE");
            float weight = getAttachedOrElse(LMDataAttachments.WEIGHT, 0.0f);
            for (int i = 36; i < 45; i++) {
                ItemStack stack = getInventory().getStack(i);
                if(stack.hasNbt() && stack.getNbt().contains(LMNBT.Flt.WEIGHT)) {
                     weight += stack.getNbt().getFloat(LMNBT.Flt.WEIGHT);
                }
            }
            setAttached(LMDataAttachments.WEIGHT, weight);
        }*/
    }

}
