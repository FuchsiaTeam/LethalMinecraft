package json.jayson.common.objects.entity;


import json.jayson.common.objects.event.listener.client.ClientEndTickListener;
import json.jayson.util.LMNBT;
import json.jayson.common.IScrapValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ScrapLootEntity extends Entity implements IScrapValue {
    private static final TrackedData<Integer> SCRAP_VALUE;
    private static final TrackedData<Integer> GRAB_TIME;
    private static final TrackedData<ItemStack> ITEM;
    public boolean renderText = false;
    public float renderTextTime = 0;

    static {
        ITEM = DataTracker.registerData(ScrapLootEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
        SCRAP_VALUE = DataTracker.registerData(ScrapLootEntity.class, TrackedDataHandlerRegistry.INTEGER);
        GRAB_TIME = DataTracker.registerData(ScrapLootEntity.class, TrackedDataHandlerRegistry.INTEGER);
    }

    public ScrapLootEntity(EntityType<?> type, World world) {
        super(type, world);
    }



    public void tick() {
        if (!this.hasItem()) {
            this.discard();
        } else {
            this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0));
            this.move(MovementType.SELF, this.getVelocity());
        }
        this.setVelocity(this.getVelocity().multiply(0.98));
    }

    @Override
    public int getScrapValue() {
        return this.getDataTracker().get(SCRAP_VALUE);
    }

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public int getGrabTime() {
        return this.getDataTracker().get(GRAB_TIME);
    }

    @Override
    public int getScrapValue(NbtCompound nbt) {
        return getScrapValue();
    }

    public boolean hasItem() {
        return !getItem().isOf(Items.AIR);
    }

    public ItemStack getItem() {
        return this.getDataTracker().get(ITEM);
    }

    public void setItem(ItemStack item) {
       this.getDataTracker().set(ITEM, item);
    }

    public void setScrapValue(int scrapValue) {
        this.getDataTracker().set(SCRAP_VALUE, scrapValue);
    }

    public void setGrabTime(int grabTime) {
        this.getDataTracker().set(GRAB_TIME, grabTime);
    }



    @Override
    protected void initDataTracker() {
        this.getDataTracker().startTracking(ITEM, new ItemStack(Items.AIR));
        this.getDataTracker().startTracking(SCRAP_VALUE, 0);
        this.getDataTracker().startTracking(GRAB_TIME, ClientEndTickListener.maxPickupCharge);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if(nbt.contains(LMNBT.SCRAP_VALUE)) {
            setScrapValue(nbt.getInt(LMNBT.SCRAP_VALUE));
        }

        if(nbt.contains(LMNBT.SCRAP_ITEM)) {
            setItem(Registries.ITEM.get(new Identifier(nbt.getString(LMNBT.SCRAP_ITEM))).getDefaultStack());
        }

        if(nbt.contains(LMNBT.GRAB_TIME)) {
            setGrabTime(nbt.getInt(LMNBT.GRAB_TIME));
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt(LMNBT.SCRAP_VALUE, getScrapValue());
        nbt.putInt(LMNBT.GRAB_TIME, getGrabTime());
        if(getItem() != null) {
            nbt.putString(LMNBT.SCRAP_ITEM, Registries.ITEM.getId(getItem().getItem()).toString());
        }
    }
}
