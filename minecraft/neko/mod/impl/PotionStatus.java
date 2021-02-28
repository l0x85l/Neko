package neko.mod.impl;

import java.util.Collection;

import neko.event.Event;
import neko.gui.hud.ScreenPosition;
import neko.mod.ModDraggable;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class PotionStatus extends ModDraggable {

    public PotionStatus() {
        super("PotionStatus", "Shows potion status on the screen");
    }

    @Override
    public int getWidth() {
        return 18 + font.getStringWidth("0:00" + "III") + 6;
    }

    @Override
    public int getHeight() {
        return 72;
    }

    @Override
    public boolean isEnabled() {
        return getState();
    }

    @Override
    public void render(ScreenPosition pos) {
            int i = 80;
            int j = 0;
            Collection<PotionEffect> collection = this.mc.thePlayer.getActivePotionEffects();
            if (!collection.isEmpty()) {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.disableLighting();
                int k = 18;
                if (collection.size() > 5) {
                    k = 132 / (collection.size() - 1);
                }
                for (PotionEffect potioneffect : this.mc.thePlayer.getActivePotionEffects()) {
                    Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    String s = I18n.format(potion.getName());
                    ResourceLocation inventoryBackground = new ResourceLocation("textures/gui/container/inventory.png");
                    mc.getTextureManager().bindTexture(inventoryBackground);
                    if (potion.hasStatusIcon()) {
                        int i1 = potion.getStatusIconIndex();
                        new Gui().drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + j, 0 + i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
                    }
                    if (potioneffect.getAmplifier() == 0) {
                        s = "";
                    } else if (potioneffect.getAmplifier() == 1) {
                        s = EnumChatFormatting.DARK_GRAY + "II";
                    } else if (potioneffect.getAmplifier() == 2) {
                        s = EnumChatFormatting.DARK_GRAY + "III";
                    } else if (potioneffect.getAmplifier() == 3) {
                        s = EnumChatFormatting.DARK_GRAY + "IV";
                    } else if (potioneffect.getAmplifier() == 4) {
                        s = EnumChatFormatting.DARK_GRAY + "V";
                    }
                    font.drawStringWithShadow(s, (float) pos.getAbsoluteX() + 43, (float) (pos.getAbsoluteY() + j + 5), 16777215);
                    String s1 = Potion.getDurationString(potioneffect);
                    font.drawStringWithShadow(EnumChatFormatting.GRAY + s1, (float) pos.getAbsoluteX() + 20, (float) (pos.getAbsoluteY() + j + 5), 8355711);
                    j += k;
            }
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        ResourceLocation inventoryBackground = new ResourceLocation("textures/gui/container/inventory.png");

        int j = 1;
        int i1 = 1;

        GlStateManager.pushMatrix();
        this.mc.getTextureManager().bindTexture(inventoryBackground);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        Gui.INSTANCE.drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY(), 18, 198, 18, 18);
        Gui.INSTANCE.drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + 18, 2 % 8 * 18, 198, 18, 18);
        Gui.INSTANCE.drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + 36, 3 % 8 * 18, 198, 18, 18);
        Gui.INSTANCE.drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + 54, 4 % 8 * 18, 198, 18, 18);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        font.drawStringWithShadow(EnumChatFormatting.GRAY + "0:00" + EnumChatFormatting.DARK_GRAY +" I", (float) pos.getAbsoluteX() + 20, (float) (pos.getAbsoluteY() + j + 5), -1);
        font.drawStringWithShadow(EnumChatFormatting.GRAY + "0:00" + EnumChatFormatting.DARK_GRAY +" II", (float) pos.getAbsoluteX() + 20, (float) (pos.getAbsoluteY() + j + 22), -1);
        font.drawStringWithShadow(EnumChatFormatting.GRAY + "0:00" + EnumChatFormatting.DARK_GRAY +" III", (float) pos.getAbsoluteX() + 20, (float) (pos.getAbsoluteY() + j + 38), -1);
        font.drawStringWithShadow(EnumChatFormatting.GRAY + "0:00" + EnumChatFormatting.DARK_GRAY +" IV", (float) pos.getAbsoluteX() + 20, (float) (pos.getAbsoluteY() + j + 55), -1);
    }

    @Override
    public void onEvent(Event event) {}
}
