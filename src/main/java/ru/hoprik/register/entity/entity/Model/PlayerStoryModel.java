package ru.hoprik.register.entity.entity.Model;// Made with Blockbench 4.6.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import ru.hoprik.register.entity.entity.Entity.PlayerStory;

public class PlayerStoryModel<T extends PlayerStory> extends EntityModel<T> {
	private final ModelRenderer Head;
	private final ModelRenderer Body;
	private final ModelRenderer RightArm;
	private final ModelRenderer RightArmLayer_r1;
	private final ModelRenderer RightArm_r1;
	private final ModelRenderer LeftArm;
	private final ModelRenderer LeftArmLayer_r1;
	private final ModelRenderer RightLeg;
	private final ModelRenderer RightLegLayer_r1;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer LeftLegLayer_r1;
	private final ModelRenderer LeftLeg_r1;

	public PlayerStoryModel() {
		textureWidth = 64;
		textureHeight = 64;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Head, -0.1047F, 0.0873F, 0.0F);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		Head.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		Body.setTextureOffset(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-5.0F, 2.0F, 3.0F);
		setRotationAngle(RightArm, -0.1745F, 0.0F, 0.1745F);
		

		RightArmLayer_r1 = new ModelRenderer(this);
		RightArmLayer_r1.setRotationPoint(5.0F, 22.0F, 0.0F);
		RightArm.addChild(RightArmLayer_r1);
		setRotationAngle(RightArmLayer_r1, 0.1745F, 0.0F, 0.0F);
		RightArmLayer_r1.setTextureOffset(40, 32).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		RightArm_r1 = new ModelRenderer(this);
		RightArm_r1.setRotationPoint(5.0F, 22.0F, 0.0F);
		RightArm.addChild(RightArm_r1);
		setRotationAngle(RightArm_r1, 0.1745F, 0.0F, 0.0F);
		RightArm_r1.setTextureOffset(40, 16).addBox(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(5.0F, 2.0F, -4.0F);
		setRotationAngle(LeftArm, 0.2094F, 0.0F, -0.1309F);
		

		LeftArmLayer_r1 = new ModelRenderer(this);
		LeftArmLayer_r1.setRotationPoint(-5.0F, 22.0F, 0.0F);
		LeftArm.addChild(LeftArmLayer_r1);
		setRotationAngle(LeftArmLayer_r1, -0.1745F, 0.0F, 0.0F);
		LeftArmLayer_r1.setTextureOffset(48, 48).addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
		LeftArmLayer_r1.setTextureOffset(32, 48).addBox(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-1.9F, 12.0F, -2.0F);
		setRotationAngle(RightLeg, 0.192F, 0.0F, 0.0349F);
		

		RightLegLayer_r1 = new ModelRenderer(this);
		RightLegLayer_r1.setRotationPoint(1.9F, 12.0F, 0.0F);
		RightLeg.addChild(RightLegLayer_r1);
		setRotationAngle(RightLegLayer_r1, -0.1745F, 0.0F, 0.0F);
		RightLegLayer_r1.setTextureOffset(0, 32).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);
		RightLegLayer_r1.setTextureOffset(0, 16).addBox(-3.9F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(1.9F, 12.0F, 2.0F);
		setRotationAngle(LeftLeg, -0.1745F, 0.0F, -0.0349F);
		

		LeftLegLayer_r1 = new ModelRenderer(this);
		LeftLegLayer_r1.setRotationPoint(-1.9F, 12.0F, 0.0F);
		LeftLeg.addChild(LeftLegLayer_r1);
		setRotationAngle(LeftLegLayer_r1, 0.1745F, 0.0F, 0.0F);
		LeftLegLayer_r1.setTextureOffset(0, 48).addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		LeftLeg_r1 = new ModelRenderer(this);
		LeftLeg_r1.setRotationPoint(-1.9F, 12.0F, 0.0F);
		LeftLeg.addChild(LeftLeg_r1);
		setRotationAngle(LeftLeg_r1, 0.1745F, 0.0F, 0.0F);
		LeftLeg_r1.setTextureOffset(16, 48).addBox(-0.1F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.Head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.Head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
	}


	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		Head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		RightArm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		LeftArm.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		RightLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		LeftLeg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}