package neko.mod;

import neko.gui.hud.HUDManager;
import neko.gui.hud.IRenderer;
import neko.main.Neko;
import neko.mod.impl.*;

import java.util.ArrayList;
import java.util.List;


public class ModManager {
    public static List<Mod> modules = new ArrayList<>();

    public ModManager(HUDManager api) {
        modules.add(new Fps());
        modules.add(new Cps());
        modules.add(new Xyz());
        modules.add(new Ping());
        modules.add(new ArmorStatus());
        modules.add(new ItemPhysics());
        modules.add(new NoBurnAnimations());
        modules.add(new PotionStatus());
        modules.add(new ToggleSprint());
        modules.add(new Keystrokes());
        modules.add(new NightVision());
        modules.add(new Sidebar());
        modules.add(new Bossbar());
        Neko.getInstance().logger.info("[" + Neko.getInstance().name + "] " + modules.size() + " mod loaded");

        getMods().parallelStream()
                .filter(mod -> mod instanceof IRenderer)
                .forEach(mod -> api.register((IRenderer) mod));
    }

    public static Mod getMod(String moduleName) {
        if (moduleName != null) {
            for (Mod mod : getMods()) {
                if (mod.getName().equalsIgnoreCase(moduleName)) {
                    return mod;
                }
            }
        }
        return null;
    }


    public static Mod getMod(final Class<? extends Mod> clazz) {
        if (clazz != null) {
            for (Mod mod : getMods()) {
                if (mod.getClass() == clazz) {
                    return mod;
                }
            }
        }
        return null;
    }


    public static List<Mod> getMods() {
        return modules;
    }
}