package json.jayson.common.objects.event.listener;

import json.jayson.common.init.LMDataAttachments;
import json.jayson.common.objects.entity.ScrapLootEntity;
import json.jayson.common.objects.item.IAmScrapLoot;
import json.jayson.common.objects.event.custom.PlayerDropItemCallback;
import json.jayson.common.init.LMEntities;
import json.jayson.network.LMNetwork;
import json.jayson.util.LMUtil;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class PlayerDropItemEventListener {

    public static void register() {
        PlayerDropItemCallback.EVENT.register((player, item) -> {
            if(item != null) {
                if (item.getStack().getItem() instanceof IAmScrapLoot scrapLoot) {
                    ItemStack stack = item.getStack();
                    if (scrapLoot.onItemDrop(player, item)) {
                        ScrapLootEntity lootEntity = new ScrapLootEntity(LMEntities.SCRAP_LOOT, player.getWorld());
                        double multiplier = 0.1d + player.getWorld().getEntitiesByClass(ScrapLootEntity.class, Box.from(player.getPos()), scrapLootEntity -> true).size() / 10.0d;
                        multiplier = Math.min(multiplier, 3);
                        Vec3d pos = player.getPos().add(LMUtil.RANDOM.nextInt(2) == 0 ? LMUtil.RANDOM.nextDouble() * multiplier : -LMUtil.RANDOM.nextDouble() * multiplier, 0.5, LMUtil.RANDOM.nextInt(2) == 0 ? LMUtil.RANDOM.nextDouble() * multiplier : -LMUtil.RANDOM.nextDouble() * multiplier);
                        lootEntity.setPosition(pos);
                        lootEntity.setItem(stack);
                        lootEntity.setScrapValue(scrapLoot.getScrapValue(stack.getNbt()));
                        lootEntity.setGrabTime(scrapLoot.getGrabTime());
                        lootEntity.setWeight(scrapLoot.getWeight(stack.getNbt()));
                        lootEntity.setYaw(LMUtil.RANDOM.nextFloat() * 360);
                        float weight = player.getAttachedOrCreate(LMDataAttachments.WEIGHT);
                        float newWeight = weight - scrapLoot.getWeight(stack.getNbt());
                        if(newWeight < 0) {
                            newWeight = 0;
                        }
                        float finalNewWeight = newWeight;
                        player.modifyAttached(LMDataAttachments.WEIGHT, w -> finalNewWeight);
                        if(player instanceof ServerPlayerEntity serverPlayerEntity) {
                            LMNetwork.Server.sendWeightUpdate(serverPlayerEntity, newWeight);
                        }
                        player.getWorld().spawnEntity(lootEntity);
                        return new ItemEntity(player.getWorld(), player.getPos().x, player.getPos().y, player.getPos().z, new ItemStack(Items.AIR));
                    }
                }
            }
            return item;
        });
    }

}
