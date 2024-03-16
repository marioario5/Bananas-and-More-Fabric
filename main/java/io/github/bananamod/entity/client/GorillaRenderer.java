package io.github.bananamod.entity.client;

import io.github.bananamod.BananaMod;
import io.github.bananamod.entity.GorillaEntity;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class GorillaRenderer extends MobEntityRenderer<GorillaEntity, GorillaModel<GorillaEntity>>{

	private static final Identifier TEXTURE = new Identifier(BananaMod.MOD_ID, "textures/entity/gorilla.png");
	
	public GorillaRenderer(Context context) {
		super(context, new GorillaModel<>(context.getPart(ModModelLayers.GORILLA)), 0.6F );
	}

	
	@Override
	public Identifier getTexture(GorillaEntity entity) {
		return TEXTURE;
	}
	
	@Override
	public void render(GorillaEntity mobEntity, float f, float g, MatrixStack matrixStack,
			VertexConsumerProvider vertexConsumerProvider, int i) {

		matrixStack.scale(1.25F, 1.25F, 1.25F);
		
		super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}

}
