package neko.mod.impl;


import neko.event.Event;
import neko.gui.hud.ScreenPosition;
import neko.mod.ModDraggable;

public class Ping extends ModDraggable {

    public Ping() {
        super("Ping", "Shows ping on the screen");
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("§8[§dPing§8] §f" + mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime());
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public boolean isEnabled() {
        return getState();
    }

    @Override
    public void render(ScreenPosition pos) {
        if (mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()) != null) {
            font.drawStringWithShadow("§8[§dPing§8] §f" + mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
        } else {
            font.drawStringWithShadow("§8[§dPing§8] §f0", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        if (mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()) != null) {
            font.drawStringWithShadow("§8[§dPing§8] §f" + mc.getNetHandler().getPlayerInfo(mc.thePlayer.getUniqueID()).getResponseTime(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
        } else {
            font.drawStringWithShadow("§8[§dPing§8] §f0", pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
        }    }

    @Override
    public void onEvent(Event event) {}
}
