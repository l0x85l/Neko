package neko.mod.impl;

import neko.event.Event;
import neko.event.EventSystem;
import neko.event.RegisterEvent;
import neko.event.impl.LoopEvent;
import neko.gui.hud.HUDManager;
import neko.gui.hud.ScreenPosition;
import neko.mod.Mod;
import neko.mod.ModDraggable;
import net.minecraft.client.Minecraft;

public class ItemPhysics extends Mod {


    public ItemPhysics() {
        super("ItemPhysics", "Realistic item physics");

    }


    @Override
    public void onEnabled() {
        saveEnabledFromFile();
        EventSystem.register(this);
    }

    @Override
    public void onDisabled() {
        saveEnabledFromFile();
        EventSystem.unregister(this);
    }



    @Override
    public void onEvent(Event event) {}
}
