package io.github.bananamod;

import io.github.bananamod.entity.client.GorillaModel;
import io.github.bananamod.entity.client.GorillaRenderer;
import io.github.bananamod.entity.client.ModModelLayers;
import io.github.bananamod.init.EntityInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@SuppressWarnings("deprecation")
public class BananaModClient implements ClientModInitializer{

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(EntityInit.GORILLA, GorillaRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GORILLA, GorillaModel::getTexturedModelData);
	}

}
