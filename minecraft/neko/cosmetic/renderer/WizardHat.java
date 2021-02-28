package neko.cosmetic.renderer;


import neko.cosmetic.Cosmetic;
import neko.cosmetic.CosmeticModelBase;
import neko.cosmetic.CosmeticTextures;
import neko.cosmetic.CosmeticUser;
import neko.irc.NekoSelf;
import neko.main.Neko;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class WizardHat extends Cosmetic {
    private final ModelWitchHat modelWitchHat;


    public WizardHat(RenderPlayer renderPlayer) {
        super(renderPlayer);
        modelWitchHat = new ModelWitchHat(renderPlayer);
    }

    @Override
    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {
            CosmeticUser cosmeticUser = Neko.getInstance().getUser(player.getNameClear());
            if (cosmeticUser.hasHat()) {
                GL11.glPushMatrix();
                playerRenderer.bindTexture(Neko.getInstance().cosmeticTextures.WIZARD_HAT);
                float[] color = {255, 255, 255};
                GL11.glColor3f(color[0], color[1], color[2]);
                modelWitchHat.render(player, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale);
                GL11.glPopMatrix();
            }
        }

    private class ModelWitchHat extends CosmeticModelBase {
        private ModelRenderer witchHat;
        
        public ModelWitchHat(RenderPlayer player) {
            super(player);
            this.witchHat = (new ModelRenderer(modelBiped)).setTextureSize(40, 34);
            this.witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
            this.witchHat.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
            ModelRenderer modelrenderer = (new ModelRenderer(modelBiped)).setTextureSize(40, 34);
            modelrenderer.setRotationPoint(1.75F, -4.0F, 2.0F);
            modelrenderer.setTextureOffset(0, 12).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
            modelrenderer.rotateAngleX = -0.05235988F;
            modelrenderer.rotateAngleZ = 0.02617994F;
            this.witchHat.addChild(modelrenderer);
            ModelRenderer modelrenderer1 = (new ModelRenderer(modelBiped)).setTextureSize(40, 34);
            modelrenderer1.setRotationPoint(1.75F, -4.0F, 2.0F);
            modelrenderer1.setTextureOffset(0, 23).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
            modelrenderer1.rotateAngleX = -0.10471976F;
            modelrenderer1.rotateAngleZ = 0.05235988F;
            modelrenderer.addChild(modelrenderer1);
            ModelRenderer modelrenderer2 = (new ModelRenderer(modelBiped)).setTextureSize(40, 34);
            modelrenderer2.setRotationPoint(1.75F, -2.0F, 2.0F);
            modelrenderer2.setTextureOffset(0, 31).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
            modelrenderer2.rotateAngleX = -0.20943952F;
            modelrenderer2.rotateAngleZ = 0.10471976F;
            modelrenderer1.addChild(modelrenderer2);
            this.witchHat.isHidden = true;
        }

        @Override
        public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale) {
            GlStateManager.pushMatrix();
            float f = 0.995F;
            GlStateManager.scale(f, f, f);
            if (entityIn.isSneaking())
            {
                GlStateManager.translate(0.0D, 0.3D, 0.0D);
            }
            GlStateManager.rotate(headYaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(headPitch, 1.0F, 0.0F, 0.0F);
            this.witchHat.isHidden = false;
            this.witchHat.render(scale);
            this.witchHat.isHidden = true;
            GlStateManager.popMatrix();
        }
    }
}