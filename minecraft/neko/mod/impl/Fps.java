package neko.mod.impl;

import neko.event.Event;
import neko.gui.hud.ScreenPosition;
import neko.mod.ModDraggable;

public class Fps extends ModDraggable {


    public Fps() {
        super("FPS", "Shows fps on the screen");
    }

    @Override
    public int getWidth() {
        return mc.fontRendererObj.getStringWidth("§8[§dFPS§8] §f" + mc.smoothFPS);
    }

    @Override
    public int getHeight() {
        return mc.fontRendererObj.FONT_HEIGHT;
    }

    @Override
    public boolean isEnabled() {
        return getState();
    }

    @Override
    public void render(ScreenPosition pos) {
        mc.getSmoothFPS();
        mc.fontRendererObj.drawStringWithShadow("§8[§dFPS§8] §f" + mc.smoothFPS, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        mc.fontRendererObj.drawStringWithShadow("§8[§dFPS§8] §f" + mc.smoothFPS, pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
    }

    @Override
    public void onEvent(Event event) {}
}
