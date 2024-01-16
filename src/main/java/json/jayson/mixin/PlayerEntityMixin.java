package json.jayson.mixin;

import com.google.common.collect.ImmutableList;
import json.jayson.LM;
import json.jayson.common.item.IAmScrapLoot;
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

    @Inject(at = @At("RETURN"), method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;")
    private void onItemDrop(CallbackInfoReturnable<ItemEntity> ci) {
        if(ci.getReturnValue().getItem() instanceof IAmScrapLoot) {
            ItemStack stack = ci.getReturnValue().getStack();
        }
        LM.LOGGER.debug(getPos().toString() + " : 1");
        ci.setReturnValue(new ItemEntity(new ItemStack(Items.AIR, getPos().x, getPos().y, getPos().z)));
    }
}
