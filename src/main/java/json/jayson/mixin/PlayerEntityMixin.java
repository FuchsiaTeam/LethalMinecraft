package json.jayson.mixin;

import com.google.common.collect.ImmutableList;
import json.jayson.LM;
import json.jayson.common.item.IAmScrapLoot;
import json.jayson.event.custom.PlayerDropItemCallback;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity{

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /* PLAYER DROP ITEM EVENT */
    //Fabric really needs an event for that, man
    @Inject(at = @At("RETURN"), method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", cancellable = true)
    private void onItemDrop(CallbackInfoReturnable<ItemEntity> ci) {
       ci.setReturnValue(PlayerDropItemCallback.EVENT.invoker().drop((PlayerEntity) (Object) this, ci.getReturnValue()));
    }
}
