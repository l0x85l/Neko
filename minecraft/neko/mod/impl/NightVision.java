package neko.mod.impl;

import neko.event.Event;
import neko.event.EventSystem;
import neko.event.RegisterEvent;
import neko.event.impl.LoopEvent;
import neko.mod.Mod;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class NightVision extends Mod {


    public NightVision() {
        super("NightVision", "Gaves infinity night vision effect");

    }


    @Override
    public void onEnabled() {
        saveEnabledFromFile();
        EventSystem.register(this);
    }

    @Override
    public void onDisabled() {
        saveEnabledFromFile();
        mc.thePlayer.removePotionEffect(Potion.nightVision.getId());
        EventSystem.unregister(this);
    }



    @Override
    @RegisterEvent(events = {LoopEvent.class})
    public void onEvent(Event event) {
        mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 5200, 1));
    }
}
