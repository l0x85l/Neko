package neko.cosmetic.renderer;


import neko.cosmetic.Cosmetic;
import neko.cosmetic.CosmeticModelBase;
import neko.cosmetic.CosmeticTextures;
import neko.cosmetic.CosmeticUser;
import neko.main.Neko;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class Bandana extends Cosmetic {
    private final ModelBandana modelBandana;


    public Bandana(RenderPlayer renderPlayer) {
        super(renderPlayer);
        modelBandana = new ModelBandana(renderPlayer);
    }

    @Override
    public void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float headYaw, float headPitch, float scale) {
        CosmeticUser cosmeticUser = Neko.getInstance().getUser(player.getNameClear());
        if(cosmeticUser.hasBandana() && Minecraft.getMinecraft().thePlayer.inventory.armorInventory[3] == null) {
            GL11.glPushMatrix();
            playerRenderer.bindTexture(Neko.getInstance().cosmeticTextures.NIKE_BANDANA);
            float[] color = {255, 255, 255};

            GL11.glColor3f(color[0], color[1], color[2]);
            modelBandana.render(player, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale);
            GL11.glPopMatrix();
        }
    }




    private class ModelBandana extends CosmeticModelBase {

        private ModelRenderer model1;
        private ModelRenderer model2;
        private ModelRenderer model3;
        private ModelRenderer model4;

        public ModelBandana(RenderPlayer player) {
            super(player);
            this.model1 = new ModelRenderer(modelBiped, 0, 0);
            this.model1.addBox(-4.5f, -7.0f, -4.7f, 9, 2, 1);
            this.model2 = new ModelRenderer(modelBiped, 0, 0);
            this.model2.addBox(3.5f, -7.0f, -3.5f, 1, 2, 8);
            this.model3 = new ModelRenderer(modelBiped, 0, 0);
            this.model3.addBox(-4.5f, -7.0f, -3.5f, 1, 2, 8);
            this.model4 = new ModelRenderer(modelBiped, 0, 0);
            this.model4.addBox(-4.5f, -7.0f, 4.0f, 9, 2, 1);
        }


        @Override
        public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scale) {
            if (entityIn.isSneaking()) {
                GL11.glTranslated(0.0, 0.225, 0.0);
            }
            this.model1.rotateAngleX = modelBiped.bipedHead.rotateAngleX;
            this.model1.rotateAngleY = modelBiped.bipedHead.rotateAngleY;
            this.model1.rotationPointX = 0.0f;
            this.model1.rotationPointY = 0.0f;
            this.model1.render(scale);
            this.model2.rotateAngleX = modelBiped.bipedHead.rotateAngleX;
            this.model2.rotateAngleY = modelBiped.bipedHead.rotateAngleY;
            this.model2.rotationPointX = 0.0f;
            this.model2.rotationPointY = 0.0f;
            this.model2.render(scale);
            this.model3.rotateAngleX = modelBiped.bipedHead.rotateAngleX;
            this.model3.rotateAngleY = modelBiped.bipedHead.rotateAngleY;
            this.model3.rotationPointX = 0.0f;
            this.model3.rotationPointY = 0.0f;
            this.model3.render(scale);
            this.model4.rotateAngleX = modelBiped.bipedHead.rotateAngleX;
            this.model4.rotateAngleY = modelBiped.bipedHead.rotateAngleY;
            this.model4.rotationPointX = 0.0f;
            this.model4.rotationPointY = 0.0f;
            this.model4.render(scale);
        }
    }
}