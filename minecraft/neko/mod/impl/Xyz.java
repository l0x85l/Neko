package neko.mod.impl;


import neko.event.Event;
import neko.gui.hud.ScreenPosition;
import neko.mod.ModDraggable;

public class Xyz extends ModDraggable {

    public Xyz() {
        super("XYZ", "Shows xyz coords on the screen");
    }

    @Override
    public int getWidth() {
        return font.getStringWidth(getXYZString());
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
        font.drawStringWithShadow(getXYZString(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        font.drawStringWithShadow(getXYZString(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    private String getXYZString() {
        return String.format("§8[§dXYZ§8] §f%.3f / %.3f / %.3f",
                mc.getRenderViewEntity().posX,
                mc.getRenderViewEntity().getEntityBoundingBox().minY,
                mc.getRenderViewEntity().posZ
        );
    }


    @Override
    public void onEvent(Event event) {}
}