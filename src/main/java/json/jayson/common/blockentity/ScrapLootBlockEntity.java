package json.jayson.common.blockentity;

import json.jayson.LMNBT;
import json.jayson.common.IScrapValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ScrapLootBlockEntity extends BlockEntity implements IScrapValue {

    private int scrapValue;
    private Item item;

    public ScrapLootBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if(nbt.contains(LMNBT.SCRAP_VALUE)) {
            scrapValue = nbt.getInt(LMNBT.SCRAP_VALUE);
        }

        if(nbt.contains(LMNBT.SCRAP_ITEM)) {
            item = Registries.ITEM.get(new Identifier(nbt.getString(LMNBT.SCRAP_ITEM)));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt(LMNBT.SCRAP_VALUE, scrapValue);
        if(item != null) {
            nbt.putString(LMNBT.SCRAP_ITEM, item.toString());
        }
    }

    @Override
    public int getScrapValue() {
        return scrapValue;
    }


    public boolean hasItem() {
        return getItem() != null;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setScrapValue(int scrapValue) {
        this.scrapValue = scrapValue;
    }
}
