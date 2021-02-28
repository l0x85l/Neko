package neko.cosmetic.renderer;

import neko.cosmetic.Cosmetic;
import neko.cosmetic.CosmeticModelBase;
import neko.cosmetic.CosmeticUser;
import neko.main.Neko;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Halo extends Cosmetic {
    private final ModelHalo modelHalo;

    public Halo(RenderPlayer renderPlayer) {
        super(renderPlayer);
        this.modelHalo = new ModelHalo(renderPlayer);
    }

    @Override
    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {
        CosmeticUser cosmeticUser = Neko.getInstance().getUser(player.getNameClear());
        if (cosmeticUser.hasHalo()) {
            GL11.glPushMatrix();
            playerRenderer.bindTexture(Neko.getInstance().cosmeticTextures.HALO);
            modelHalo.render(player, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale);
            GL11.glPopMatrix();
        }
    }

    private class ModelHalo extends CosmeticModelBase {
        private ModelRenderer halo;

        public ModelHalo(RenderPlayer player) {
            super(player);
            this.halo = (new ModelRenderer(modelBiped)).setTextureSize(14, 2);
            this.halo.addBox(-3.0F, -12.5F, -4.0F, 6, 1, 1, 0.15f);
            this.halo.isHidden = true;
        }

        @Override
        public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale) {
            GlStateManager.pushMatrix();
            float f = (float) Math.cos((double) ageInTicks / 10.0D) / 20.0F;
            GlStateManager.rotate(headYaw + ageInTicks / 2.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(0.0F, f - 0.2F, 0.0F);
            GlStateManager.disableLighting();
            ModelRenderer modelrenderer = bindTextureAndColor(Color.YELLOW, Neko.getInstance().cosmeticTextures.HALO, this.halo, null);
            modelrenderer.isHidden = false;
            if (entityIn.isSneaking()) {
                GlStateManager.translate(0.0D, 0.3D, 0.0D);
            }
            for (int i = 0; i < 4; ++i) {
                modelrenderer.render(scale);
                GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            }

            modelrenderer.isHidden = true;
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
}
