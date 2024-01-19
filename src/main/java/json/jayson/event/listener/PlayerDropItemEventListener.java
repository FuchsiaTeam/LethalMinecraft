package json.jayson.event.listener;

import json.jayson.common.IScrapValue;
import json.jayson.common.blockentity.ScrapLootBlockEntity;
import json.jayson.common.entity.ScrapLootEntity;
import json.jayson.common.item.IAmScrapLoot;
import json.jayson.event.custom.PlayerDropItemCallback;
import json.jayson.init.LMBlocks;
import json.jayson.init.LMEntities;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;

public class PlayerDropItemEventListener {

    public static void register() {
        PlayerDropItemCallback.EVENT.register((player, item) -> {
            if(item != null) {
                if (item.getStack().getItem() instanceof IAmScrapLoot scrapLoot) {
                    ItemStack stack = item.getStack();
                    if (scrapLoot.onItemDrop(player, item)) {
                        /*for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                BlockPos pos = player.getBlockPos().add(i, 0, j);
                                if (player.getWorld().getBlockState(pos).getBlock().equals(Blocks.AIR)) {
                                    player.getWorld().setBlockState(pos, LMBlocks.SCRAP_LOOT.getDefaultState());
                                    BlockEntity blockEntity = player.getWorld().getBlockEntity(pos);
                                    if (blockEntity instanceof ScrapLootBlockEntity scrapLootBlockEntity) {
                                        scrapLootBlockEntity.setScrapValue(((IScrapValue) stack.getItem()).getScrapValue(stack.getNbt()));
                                        scrapLootBlockEntity.setItem(stack.getItem());
                                        scrapLootBlockEntity.markDirty();
                                    }
                                    return new ItemEntity(player.getWorld(), player.getPos().x, player.getPos().y, player.getPos().z, new ItemStack(Items.AIR));
                                }
                            }
                        }*/
                        ScrapLootEntity lootEntity = new ScrapLootEntity(LMEntities.SCRAP_LOOT, player.getWorld());
                        lootEntity.setPosition(player.getPos());
                        lootEntity.setItem(stack.getItem());
                        player.getWorld().spawnEntity(lootEntity);
                        return new ItemEntity(player.getWorld(), player.getPos().x, player.getPos().y, player.getPos().z, new ItemStack(Items.AIR));
                    }
                }
            }
            return item;
        });
    }

}
