package neko.cosmetic;

import neko.main.Neko;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public abstract class Cosmetic implements LayerRenderer<AbstractClientPlayer> {
    protected final RenderPlayer playerRenderer;
    private float partialTicks;

    public Cosmetic(RenderPlayer playerRenderer) {
        this.playerRenderer = playerRenderer;
    }

    @Override
    public void doRenderLayer(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if(Neko.getInstance().getCosmetic().canRender(player) && player.hasPlayerInfo() && !player.isInvisible()) {
            render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
        }

    }
    public abstract void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale);


    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    protected void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public float getPartialTicks()
    {
        return this.partialTicks;
    }

    public void setPartialTicks(float partialTicks)
    {
        this.partialTicks = partialTicks;
    }

    private static float Sigmoid(double value) {
        return 1.0F / (1.0F + (float) Math.exp(-value));
    }

    private float getAnimationTime(int totalTime, int offset) {
        float time = (float) ((System.currentTimeMillis() + (long) offset) % (long) totalTime);

        return time / (float) totalTime;
    }

    public float interpolate(final float spray, final float receive, final float proud) {
        float pregnant = (spray + (receive - spray) * proud) % 360.0f;
        if (pregnant < 0.0f) {
            pregnant += 360.0f;
        }
        return pregnant;
    }

    protected float getWingAngle(boolean isFlying, float maxAngle, int totalTime, int flyingTime, int offset) {
        float angle = 0.0F;
        int flapTime = totalTime;

        if (isFlying) {
            flapTime = flyingTime;
        }

        float deltaTime = this.getAnimationTime(flapTime, offset);

        if (deltaTime <= 0.5F) {
            angle = Sigmoid((double) (-4.0F + deltaTime * 2.0F * 8.0F));
        } else {
            angle = 1.0F - Sigmoid((double) (-4.0F + (deltaTime * 2.0F - 1.0F) * 8.0F));
        }

        angle *= maxAngle;
        return angle;
    }
    protected ModelRenderer bindTextureAndColor(Color color, ResourceLocation resourceLocation, ModelRenderer colorModel, ModelRenderer playerSkinModel)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        boolean flag = false;

        if (color != null)
        {
            if (playerSkinModel != null && color.getRed() == 0 && color.getGreen() == 0 && color.getBlue() == 0)
            {
                flag = true;
                colorModel = playerSkinModel;
            }
            else
            {
                GL11.glColor4f((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, 1.0F);
            }
        }

        if (!flag)
        {
            Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
            GlStateManager.resetColor();
        }

        return colorModel;
    }
}
