package json.jayson.mixin;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    @Mutable
    @Shadow @Final public DefaultedList<ItemStack> main;

    @Inject(method = "<init>*", at = @At("TAIL"))
    public void constructorHead(CallbackInfo ci) {
        //FUNNY SHIT
        //this.main = DefaultedList.ofSize(36, new ItemStack(Items.WEATHERED_COPPER_BULB));
    }

}
