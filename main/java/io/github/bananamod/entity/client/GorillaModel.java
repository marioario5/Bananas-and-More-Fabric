package io.github.bananamod.entity.client;

import io.github.bananamod.entity.GorillaEntity;
import io.github.bananamod.entity.animation.ModAnimations;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class GorillaModel<T extends GorillaEntity> extends SinglePartEntityModel<T>{
	private final ModelPart gorilla;
	private final ModelPart head;

	public GorillaModel(ModelPart root) {
		this.gorilla = root.getChild("gorilla");
		this.head = gorilla.getChild("body").getChild("torso").getChild("head");
	}
	@SuppressWarnings("unused")
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData gorilla = modelPartData.addChild("gorilla", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData body = gorilla.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -5.0F, 10.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.0F, -10.0F));

		ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(15, 0).cuboid(5.0F, -7.0F, 1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
		.uv(38, 31).cuboid(-6.0F, -7.0F, 1.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -18.0F, -11.0F));

		ModelPartData skull = head.addChild("skull", ModelPartBuilder.create().uv(46, 0).cuboid(-5.0F, -26.0F, -12.0F, 10.0F, 10.0F, 9.0F, new Dilation(0.0F))
		.uv(38, 31).cuboid(-4.0F, -27.0F, -11.0F, 8.0F, 1.0F, 7.0F, new Dilation(0.0F))
		.uv(0, 31).cuboid(-3.0F, -22.0F, -14.0F, 6.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 18.0F, 11.0F));

		ModelPartData chest = torso.addChild("chest", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -8.0F, -9.0F, 12.0F, 9.0F, 22.0F, new Dilation(0.0F))
		.uv(0, 31).cuboid(-5.0F, -9.0F, -5.0F, 10.0F, 1.0F, 18.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -9.0F, -1.0F, -0.2182F, 0.0F, 0.0F));

		ModelPartData front_right_leg = body.addChild("front_right_leg", ModelPartBuilder.create(), ModelTransform.pivot(5.5F, -10.0F, -18.5F));

		ModelPartData front_right_hand = front_right_leg.addChild("front_right_hand", ModelPartBuilder.create().uv(56, 39).cuboid(-2.5F, -0.5F, -2.5F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.5F, -1.0F));

		ModelPartData front_right_arm = front_right_leg.addChild("front_right_arm", ModelPartBuilder.create().uv(20, 50).cuboid(-2.5F, 0.0F, -3.5F, 5.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData front_left_leg = body.addChild("front_left_leg", ModelPartBuilder.create(), ModelTransform.pivot(-5.5F, -10.0F, -18.5F));

		ModelPartData front_left_hand = front_left_leg.addChild("front_left_hand", ModelPartBuilder.create().uv(55, 58).cuboid(-2.5F, -0.5F, -2.5F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 8.5F, -1.0F));

		ModelPartData front_left_arm = front_left_leg.addChild("front_left_arm", ModelPartBuilder.create().uv(40, 50).cuboid(-2.5F, 0.0F, -3.5F, 5.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData back_left_leg = gorilla.addChild("back_left_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 15.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(5.5F, -13.0F, 10.5F));

		ModelPartData back_right_leg = gorilla.addChild("back_right_leg", ModelPartBuilder.create().uv(0, 50).cuboid(-2.5F, 0.0F, -2.5F, 5.0F, 15.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.5F, -13.0F, 10.5F));
		return TexturedModelData.of(modelData, 128, 128);
	}
	@Override
	public void setAngles(GorillaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);
		
		this.updateAnimation(entity.idleAnimationState, ModAnimations.IDLE, ageInTicks, 1f);
		this.updateAnimation(entity.attackAnimationState, ModAnimations.ATTACK, ageInTicks, 1f);
		this.updateAnimation(entity.poundAnimationState, ModAnimations.POUND, ageInTicks, 1f);
	}
	
	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}
	
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		gorilla.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}
	
	@Override
	public ModelPart getPart() {
		return gorilla;
	}
	
}