package json.jayson.common.objects.blockentity;

import org.jetbrains.annotations.Nullable;

import json.jayson.common.IScrapValue;
import json.jayson.util.LMNBT;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

@Deprecated
public class ScrapLootBlockEntity extends BlockEntity implements IScrapValue {

    private int scrapValue;
    private Item item;

    public ScrapLootBlockEntity(BlockPos pos, BlockState state) {
        super(null, pos, state);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if(nbt.contains(LMNBT.Int.SCRAP_VALUE)) {
            scrapValue = nbt.getInt(LMNBT.Int.SCRAP_VALUE);
        }

        if(nbt.contains(LMNBT.Str.SCRAP_ITEM)) {
            item = Registries.ITEM.get(new Identifier(nbt.getString(LMNBT.Str.SCRAP_ITEM)));
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt(LMNBT.Int.SCRAP_VALUE, scrapValue);
        if(item != null) {
            nbt.putString(LMNBT.Str.SCRAP_ITEM, Registries.ITEM.getId(item).toString());
        }
    }

    @Override
    public int getScrapValue() {
        return scrapValue;
    }

    @Override
    public int getScrapValue(NbtCompound nbt) {
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


    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
