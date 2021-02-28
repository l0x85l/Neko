package neko.event.impl;

import neko.event.Event;
import net.minecraft.client.gui.ScaledResolution;

public class RenderGuiEvent extends Event {
    private ScaledResolution resolution;

    public void fire(ScaledResolution resolution) {
        this.resolution = resolution;
        super.fire();
    }

    public ScaledResolution getResolution() {
        return resolution;
    }
}
