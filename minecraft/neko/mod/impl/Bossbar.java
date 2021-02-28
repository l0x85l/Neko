package neko.mod.impl;

import neko.event.Event;
import neko.gui.hud.ScreenPosition;
import neko.mod.ModDraggable;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.boss.BossStatus;

public class Bossbar extends ModDraggable {

    public Bossbar() {
        super("Bossbar", "Shows bossbar on the screen");
    }

    @Override
    public int getWidth() {
        return 182;
    }

    @Override
    public int getHeight() {
        return 18;
    }

    @Override
    public boolean isEnabled() {
        return getState();
    }


    int offset = 13;

    @Override
    public void render(ScreenPosition pos) {
        if (BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
            this.mc.getTextureManager().bindTexture(Gui.icons);
            --BossStatus.statusBarTime;
            this.mc.getTextureManager().bindTexture(Gui.icons);
            int j = 182;
            this.mc.getTextureManager().bindTexture(Gui.icons);
            int l = (int) (BossStatus.healthScale * (float) (j + 1));
            Gui.INSTANCE.drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + offset, 0, 74, j, 5);
            Gui.INSTANCE.drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + offset, 0, 74, j, 5);
            if (l > 0) {
                Gui.INSTANCE.drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + offset, 0, 79, l, 5);
            }
            this.mc.getTextureManager().bindTexture(Gui.icons);

            String s = BossStatus.bossName;

            font.drawStringWithShadow(s, (((this.getWidth() / 2) - (font.getStringWidth(s) / 2)) + pos.getAbsoluteX()), (pos.getAbsoluteY() - 10) + offset, 16777215);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(Gui.icons);
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        this.mc.getTextureManager().bindTexture(Gui.icons);
        --BossStatus.statusBarTime;
        this.mc.getTextureManager().bindTexture(Gui.icons);
        int j = 182;
        this.mc.getTextureManager().bindTexture(Gui.icons);
        int l = (int)(BossStatus.healthScale * (float)(j + 1));
            Gui.INSTANCE.drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + offset + 1, 0, 74, j, 5);
            Gui.INSTANCE.drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + offset + 1, 0, 74, j, 5);
        if (l > 0)
        {
            Gui.INSTANCE.drawTexturedModalRect(pos.getAbsoluteX(), pos.getAbsoluteY() + offset + 1, 0, 79, l, 5);
        }
        this.mc.getTextureManager().bindTexture(Gui.icons);

        String s = "Bossbar";

        font.drawStringWithShadow(s, (((this.getWidth() / 2) - (font.getStringWidth(s) / 2)) + pos.getAbsoluteX()), (pos.getAbsoluteY() - 10) + offset, 16777215);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Gui.icons);
    }

    @Override
    public void onEvent(Event event) {}
}