package json.jayson.util;

import json.jayson.LM;
import json.jayson.common.init.LMDimensions;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.PlaceCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructureStart;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.structure.Structure;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class LMMoonUtil {

    public static boolean extractExperimentation(MinecraftServer server) {
        return extractMoon(FabricLoader.getInstance().getModContainer(LM.ID).get(), server, LMDimensions.MOON_EXPERIMENTATION_WORLD, "data/lm/moons/experimentation/region/");
    }

    public static boolean extractMoon(ModContainer modContainer, MinecraftServer server, RegistryKey<World> dim, String dataPath) {
        Path moon = modContainer.findPath(dataPath).get();
        try (Stream<Path> moonPath = Files.list(moon)) {
            //server.getWorld(dim).close();
            String dimPath = LMServerUtil.getCurrentSaveFull(server) + "dimensions/"+ dim.getValue().getNamespace() + "/" + dim.getValue().getPath();
            FileUtils.deleteDirectory(new File(dimPath + "/"));
            FileUtils.forceMkdir(new File(dimPath + "/region/"));
            for (Path path : moonPath.toList()) {
                FileUtils.copyFile(path.toFile(), new File(dimPath + path.toString().substring(path.toString().indexOf("\\region\\"))));
            }
        } catch (IOException e) {
            LM.LOGGER.warn("Could not extract moon", e);
        }
        return true;
    }


    public static void spawnDungeonLootStructure(ServerWorld serverWorld, Structure structure, BlockPos pos) {
        ChunkGenerator chunkGenerator = serverWorld.getChunkManager().getChunkGenerator();
        StructureStart structureStart = structure.createStructureStart(serverWorld.getServer().getRegistryManager(), chunkGenerator, chunkGenerator.getBiomeSource(), serverWorld.getChunkManager().getNoiseConfig(), serverWorld.getStructureTemplateManager(), serverWorld.getSeed(), new ChunkPos(pos), 0, serverWorld, (biome) -> true);
            BlockBox blockBox = structureStart.getBoundingBox();
            ChunkPos chunkPos = new ChunkPos(ChunkSectionPos.getSectionCoord(blockBox.getMinX()), ChunkSectionPos.getSectionCoord(blockBox.getMinZ()));
            ChunkPos chunkPos2 = new ChunkPos(ChunkSectionPos.getSectionCoord(blockBox.getMaxX()), ChunkSectionPos.getSectionCoord(blockBox.getMaxZ()));
            ChunkPos.stream(chunkPos, chunkPos2).forEach((chunkPosx) -> structureStart.place(serverWorld, serverWorld.getStructureAccessor(), chunkGenerator, serverWorld.getRandom(), new BlockBox(chunkPosx.getStartX(), serverWorld.getBottomY(), chunkPosx.getStartZ(), chunkPosx.getEndX(), serverWorld.getTopY(), chunkPosx.getEndZ()), chunkPosx));
    }

    public static void spawnDungeonLootStructure(ServerWorld serverWorld) {
        spawnDungeonLootStructure(serverWorld, serverWorld.getRegistryManager().get(RegistryKeys.STRUCTURE).get(LMUtil.createLocation("")), new BlockPos(0, 100, 0));
    }

}
