package baguchan.revampedwolf.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.WolfRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WolfHeldItemLayer extends RenderLayer<WolfRenderState, WolfModel> {
	private final ItemRenderer itemInHandRenderer;

	public WolfHeldItemLayer(RenderLayerParent<WolfRenderState, WolfModel> p_116994_, ItemRenderer p_234839_) {
		super(p_116994_);
		this.itemInHandRenderer = p_234839_;
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, WolfRenderState wolfRenderState, float v, float v1) {
		if (this.getParentModel() instanceof HeadedModel headedModel) {
			BakedModel bakedmodel = wolfRenderState.getMainHandItemModel();
			if (bakedmodel != null) {
				boolean flag1 = wolfRenderState.isBaby;
				poseStack.pushPose();
				headedModel.getHead().translateAndRotate(poseStack);
				float scale = flag1 ? 0.75F : 1.0F;
				poseStack.translate(0, 0.1F * (flag1 ? 4.0F : 1.25F), (flag1 ? -0.25F : -0.5F));
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0f));
				ItemStack itemStack = wolfRenderState.getMainHandItem();
				this.itemInHandRenderer.render(itemStack, ItemDisplayContext.GROUND, false, poseStack, multiBufferSource, i, OverlayTexture.NO_OVERLAY, bakedmodel);
				poseStack.popPose();
			}
		}
	}

	public void render(PoseStack poseStack, MultiBufferSource p_117008_, int p_117009_, Wolf p_117010_, float p_117011_, float p_117012_, float p_117013_, float p_117014_, float p_117015_, float p_117016_) {

	}
}