package neko.mod.impl;

import neko.event.Event;
import neko.gui.hud.ScreenPosition;
import neko.mod.ModDraggable;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;

public class Cps extends ModDraggable {

    private ArrayList<Long> clicks = new ArrayList();
    private boolean wasPressed;
    private long lastPressed;

    public Cps() {
        super("CPS", "Shows cps on the screen");
    }

    @Override
    public int getWidth() {
        return font.getStringWidth("§8[§dCPS§8] §f" + getCPS());
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
        boolean flag1 = Mouse.isButtonDown(0);
        if (flag1 != this.wasPressed)
        {
            this.lastPressed = System.currentTimeMillis();
            this.wasPressed = flag1;

            if (flag1)
            {
                this.clicks.add(this.lastPressed);
            }
        }
        font.drawStringWithShadow("§8[§dCPS§8] §f" + getCPS(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        boolean flag1 = Mouse.isButtonDown(0);
        if (flag1 != this.wasPressed)
        {
            this.lastPressed = System.currentTimeMillis();
            this.wasPressed = flag1;

            if (flag1)
            {
                this.clicks.add(this.lastPressed);
            }
        }
        font.drawStringWithShadow("§8[§dCPS§8] §f" + getCPS(), pos.getAbsoluteX() + 1, pos.getAbsoluteY() + 1, -1);
    }

    private int getCPS()
    {
        long times = System.currentTimeMillis();
        this.clicks.removeIf(aLong -> aLong + 1000 < times);
        return this.clicks.size();
    }

    @Override
    public void onEvent(Event event) {}
}