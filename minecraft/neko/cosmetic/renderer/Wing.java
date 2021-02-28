package neko.cosmetic.renderer;

import neko.cosmetic.Cosmetic;
import neko.cosmetic.CosmeticModelBase;
import neko.cosmetic.CosmeticTextures;
import neko.cosmetic.CosmeticUser;
import neko.main.Neko;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.UUID;

public class Wing extends Cosmetic {
    private final ModelWing modelDragonWings;

    public Wing(RenderPlayer renderPlayer) {
        super(renderPlayer);
        modelDragonWings = new ModelWing(renderPlayer);
    }

    @Override
    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {
        CosmeticUser cosmeticUser = Neko.getInstance().getUser(player.getNameClear());
        if (cosmeticUser.hasWing()) {
            GL11.glPushMatrix();
            playerRenderer.bindTexture(Neko.getInstance().cosmeticTextures.DRAGON_WING);
            float[] color = {255, 255, 255};
            GL11.glColor3f(color[0], color[1], color[2]);
            modelDragonWings.render(player, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale);
            GL11.glPopMatrix();
        }
    }

    private class ModelWing extends CosmeticModelBase {
        private ModelRenderer wing;
        private ModelRenderer wingTip;
        boolean flying;

        public ModelWing(RenderPlayer player) {
            super(player);
            this.flying = false;
            this.setTextureOffset("wingtip.bone", 112, 136);
            this.setTextureOffset("wing.skin", -56, 88);
            this.setTextureOffset("wing.bone", 112, 88);
            this.setTextureOffset("wingtip.skin", -56, 144);
            final int bw = this.textureWidth;
            final int bh = this.textureWidth;
            this.textureWidth = 256;
            this.textureWidth = 256;
            (this.wing = new ModelRenderer(this, "wing")).setTextureSize(256, 256);
            this.wing.setRotationPoint(-12.0f, 5.0f, 2.0f);
            this.wing.addBox("bone", -56.0f, -4.0f, -4.0f, 56, 8, 8);
            this.wing.addBox("skin", -56.0f, 0.0f, 2.0f, 56, 0, 56);
            this.wing.isHidden = true;
            (this.wingTip = new ModelRenderer(this, "wingtip")).setTextureSize(256, 256);
            this.wingTip.setRotationPoint(-56.0f, 0.0f, 0.0f);
            this.wingTip.isHidden = true;
            this.wingTip.addBox("bone", -56.0f, -2.0f, -2.0f, 56, 4, 4);
            this.wingTip.addBox("skin", -56.0f, 0.0f, 2.0f, 56, 0, 56);
            this.wing.addChild(this.wingTip);
            this.textureWidth = bw;
            this.textureWidth = bh;
        }

        @Override
        public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale) {
            GL11.glPushMatrix();
            float anSpeed = 100.0f;
            if (!entityIn.onGround || this.flying) {
                anSpeed = 10.0f;
                this.flying = true;
            }
            if (entityIn.isSneaking()) {
                GlStateManager.translate(0.0f, 0.2f, 0.02f);
            }
            final float f1 = limbSwingAmount + ageInTicks / anSpeed;
            final float f2 = limbSwingAmount + ageInTicks / 100.0f;
            final float f3 = f1 * 3.1415927f * 2.0f;
            final float f4 = 0.125f - (float) Math.cos(f3) * 0.2f;
            final float fs5 = f2 * 3.1415927f * 2.0f;
            final float f5 = 0.125f - (float) Math.cos(fs5) * 0.2f;
            if (this.flying && (int) (f4 * 100.0f) == (int) (f5 * 100.0f)) {
                this.flying = false;
                anSpeed = 100.0f;
            }
            GlStateManager.scale(0.15, 0.15, 0.15);
            GlStateManager.translate(0.0, 0.01, 1.1);
            GlStateManager.rotate(50.0f, -50.0f, 0.0f, 0.0f);
            for (int i = 0; i < 2; ++i) {
                final float f6 = f1 * 3.1415927f * 2.0f;
                this.wing.rotateAngleX = 0.125f - (float) Math.cos(f6) * 0.2f;
                this.wing.rotateAngleY = 0.25f;
                this.wing.rotateAngleZ = (float) (Math.sin(f6) + 1.225) * 0.3f;
                this.wingTip.rotateAngleZ = -(float) (Math.sin(f6 + 2.0f) + 0.5) * 0.75f;
                this.wing.isHidden = false;
                this.wingTip.isHidden = false;
                this.wing.render(scale);
                this.wing.isHidden = true;
                this.wingTip.isHidden = true;
                GlStateManager.color(1.0f, 1.0f, 1.0f);
                if (i == 0) {
                    GlStateManager.scale(-1.0f, 1.0f, 1.0f);
                }
            }
            GL11.glPopMatrix();
        }
    }
}
