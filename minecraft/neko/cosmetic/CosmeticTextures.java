package neko.cosmetic;

import neko.event.Event;
import neko.event.EventListener;
import neko.event.EventSystem;
import neko.event.RegisterEvent;
import neko.event.impl.LoopEvent;
import neko.main.Neko;
import neko.utils.AnimatedResourceLocation;
import neko.utils.FileUtils;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.util.HashMap;

public class CosmeticTextures implements EventListener {
    public AnimatedResourceLocation REM_TEXTURE = new AnimatedResourceLocation("neko/cosmetic/capes/animated/rem", 7, 1);
    public ResourceLocation AHEGAO = new ResourceLocation("neko/cosmetic/capes/normal/ahegao.png");
    public ResourceLocation WIZARD_HAT = new ResourceLocation("neko/cosmetic/hat/wizardhat.png");
    public ResourceLocation NIKE_BANDANA = new ResourceLocation("neko/cosmetic/bandanas/nike.png");
    public ResourceLocation DRAGON_WING = new ResourceLocation("neko/cosmetic/wing/dragon.png");
    public ResourceLocation HALO = new ResourceLocation("neko/cosmetic/halo/halo.png");


    public HashMap<String, AnimatedResourceLocation> animatedCapes = new HashMap<>();
    public HashMap<String, ResourceLocation> normalCapes = new HashMap<>();
    public HashMap<String, ResourceLocation> hats = new HashMap<>();
    public HashMap<String, ResourceLocation> bandanas = new HashMap<>();
    public HashMap<String, ResourceLocation> wings = new HashMap<>();
    public HashMap<String, ResourceLocation> halo = new HashMap<>();

    public CosmeticTextures() {
        EventSystem.register(this);
        animatedCapes.put("rem", REM_TEXTURE);
        normalCapes.put("ahegao", AHEGAO);
        hats.put("wizard", WIZARD_HAT);
        halo.put("angel", HALO);
        bandanas.put("nike", NIKE_BANDANA);
        wings.put("dragon", DRAGON_WING);
    }




    public ResourceLocation getHat(String name) { return hats.getOrDefault(name, null); }

    public ResourceLocation getHalo(String name) { return halo.getOrDefault(name, null); }

    public ResourceLocation getWing(String name) { return wings.getOrDefault(name, null); }

    public ResourceLocation getBandana(String name) { return bandanas.getOrDefault(name, null); }

    public Object getCape(String name) {
        if (animatedCapes.containsKey(name)) {
            return animatedCapes.get(name);
        }
        return normalCapes.getOrDefault(name, null);
    }


    public String loadCapesFromFile() {
        return FileUtils.readFromJson(new File(FileUtils.getCosmeticsDir(), "capes.json"), String.class);
    }
    

    public void saveCapesFromFile() {
        FileUtils.writeJsonToFile(new File(FileUtils.getCosmeticsDir(), "capes.json"), Neko.getInstance().getCosmetic().cape());
    }

    public void saveHatsFromFile() {
        FileUtils.writeJsonToFile(new File(FileUtils.getCosmeticsDir(), "hats.json"), Neko.getInstance().getCosmetic().hat());
    }

    public String loadHatsFromFile() {
        return FileUtils.readFromJson(new File(FileUtils.getCosmeticsDir(), "hats.json"), String.class);
    }

    public String loadBandanasFromFile() {
        return FileUtils.readFromJson(new File(FileUtils.getCosmeticsDir(), "bandanas.json"), String.class);
    }

    public void saveBandanasFromFile() {
        FileUtils.writeJsonToFile(new File(FileUtils.getCosmeticsDir(), "bandanas.json"), Neko.getInstance().getCosmetic().bandana());
    }

    public String loadWingsFromFile() {
        return FileUtils.readFromJson(new File(FileUtils.getCosmeticsDir(), "wings.json"), String.class);
    }

    public void saveWingsFromFile() {
        FileUtils.writeJsonToFile(new File(FileUtils.getCosmeticsDir(), "wings.json"), Neko.getInstance().getCosmetic().wing());
    }

    public String loadHalosFromFile() {
        return FileUtils.readFromJson(new File(FileUtils.getCosmeticsDir(), "halos.json"), String.class);
    }

    public void saveHalosFromFile() {
        FileUtils.writeJsonToFile(new File(FileUtils.getCosmeticsDir(), "halos.json"), Neko.getInstance().getCosmetic().halo());
    }



    @RegisterEvent(events = {LoopEvent.class})
    public void onEvent(Event event) {
        for (AnimatedResourceLocation value : animatedCapes.values()) {
            value.update();
        }
    }
}
