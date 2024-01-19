package json.jayson.common.entity;


import json.jayson.LMNBT;
import json.jayson.common.IScrapValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ScrapLootEntity extends Entity implements IScrapValue {
    private int scrapValue;
    private Item item;

    public ScrapLootEntity(EntityType<?> type, World world) {
        super(type, world);
    }



    public void tick() {
        if (!this.hasItem()) {
            //this.discard();
        } else {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
            this.move(MovementType.SELF, this.getVelocity());
        }
        this.setVelocity(this.getVelocity().multiply(0.98));
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


    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if(nbt.contains(LMNBT.SCRAP_VALUE)) {
            scrapValue = nbt.getInt(LMNBT.SCRAP_VALUE);
        }

        if(nbt.contains(LMNBT.SCRAP_ITEM)) {
            item = Registries.ITEM.get(new Identifier(nbt.getString(LMNBT.SCRAP_ITEM)));
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt(LMNBT.SCRAP_VALUE, scrapValue);
        if(item != null) {
            nbt.putString(LMNBT.SCRAP_ITEM, Registries.ITEM.getId(item).toString());
        }
    }
}
