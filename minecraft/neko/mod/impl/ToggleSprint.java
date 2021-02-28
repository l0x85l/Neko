package neko.mod.impl;


import neko.event.Event;
import neko.gui.hud.ScreenPosition;
import neko.mod.ModDraggable;

public class ToggleSprint extends ModDraggable {

    public boolean flyBoost = true;
    public float flyBoostFactor = 3;
    public int keyHoldTicks = 7;

    public ToggleSprint() {
        super("ToggleSprint", "toggles auto sprint while walking");
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("[Sprinting (Key Toggled)]");
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
        this.font.drawStringWithShadow(mc.thePlayer.movementInput.getDisplayText(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        this.font.drawStringWithShadow("[Sprinting (Key Toggled)]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public void onEvent(Event event) {}
}