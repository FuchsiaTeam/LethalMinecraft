package json.jayson.data.listener;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import json.jayson.data.LMMoon;
import json.jayson.util.LMIdentifier;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

public class MoonReloadListener implements SimpleResourceReloadListener<LMMoon> {


    @Override
    public CompletableFuture<LMMoon> load(ResourceManager manager, Profiler profiler, Executor executor) {
        return null;
    }

    @Override
    public CompletableFuture<Void> apply(LMMoon data, ResourceManager manager, Profiler profiler, Executor executor) {
        return null;
    }

    @Override
    public Identifier getFabricId() {
        return LMIdentifier.lm("moons");
    }
}
