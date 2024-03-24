package io.github.bananamod;

import io.github.bananamod.entity.client.GorillaModel;
import io.github.bananamod.entity.client.GorillaRenderer;
import io.github.bananamod.entity.client.ModModelLayers;
import io.github.bananamod.init.BlockInit;
import io.github.bananamod.init.EntityInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;

@SuppressWarnings("deprecation")
public class BananaModClient implements ClientModInitializer{

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(EntityInit.GORILLA, GorillaRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GORILLA, GorillaModel::getTexturedModelData);
		
		BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.BANANA_LEAVES, RenderLayer.getCutout());
		
		
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            BlockState blockState = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
            return MinecraftClient.getInstance().getBlockColors().getColor(blockState, null, null, tintIndex);
        }, BlockInit.BANANA_LEAVES);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return FoliageColors.getDefaultColor();
            }
            return BiomeColors.getFoliageColor(world, pos);
        }, BlockInit.BANANA_LEAVES);
        
	}

}
