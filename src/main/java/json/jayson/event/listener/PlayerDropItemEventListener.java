package json.jayson.event.listener;

import json.jayson.common.IScrapValue;
import json.jayson.common.blockentity.ScrapLootBlockEntity;
import json.jayson.common.entity.ScrapLootEntity;
import json.jayson.common.item.IAmScrapLoot;
import json.jayson.event.custom.PlayerDropItemCallback;
import json.jayson.init.LMBlocks;
import json.jayson.init.LMEntities;
import json.jayson.util.LMUtil;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class PlayerDropItemEventListener {

    public static void register() {
        PlayerDropItemCallback.EVENT.register((player, item) -> {
            if(item != null) {
                if (item.getStack().getItem() instanceof IAmScrapLoot scrapLoot) {
                    ItemStack stack = item.getStack();
                    if (scrapLoot.onItemDrop(player, item)) {
                        ScrapLootEntity lootEntity = new ScrapLootEntity(LMEntities.SCRAP_LOOT, player.getWorld());
                        Vec3d pos = player.getPos().add(LMUtil.RANDOM.nextDouble() * 1.2, 0, LMUtil.RANDOM.nextDouble() * 1.2);
                        lootEntity.setPosition(pos);
                        lootEntity.setItem(stack);
                        lootEntity.setScrapValue(scrapLoot.getScrapValue(stack.getNbt()));
                        lootEntity.setYaw(LMUtil.RANDOM.nextFloat() * 360);
                        player.getWorld().spawnEntity(lootEntity);
                        return new ItemEntity(player.getWorld(), player.getPos().x, player.getPos().y, player.getPos().z, new ItemStack(Items.AIR));
                    }
                }
            }
            return item;
        });
    }

}
