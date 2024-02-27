package json.jayson.data.listener;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import json.jayson.data.LMMoon;
import json.jayson.util.LMIdentifier;
import json.jayson.util.LMUtil;
import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

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
