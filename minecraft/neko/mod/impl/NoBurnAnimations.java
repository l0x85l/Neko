package neko.mod.impl;

import neko.event.Event;
import neko.event.EventSystem;
import neko.mod.Mod;

public class NoBurnAnimations extends Mod {


    public NoBurnAnimations() {
        super("NoBurnAnimations", "Remove the burning animation");

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
