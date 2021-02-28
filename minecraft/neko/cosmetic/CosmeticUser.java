package neko.cosmetic;

import neko.irc.NekoSelf;
import neko.main.Neko;
import neko.utils.AnimatedResourceLocation;
import neko.utils.FileUtils;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.io.File;

public class CosmeticUser {


    private String name;
    private String cape;
    private String hat;
    private String bandana;
    private String wing;
    private String halo;


    private ResourceLocation haloRes;
    private ResourceLocation hatRes;
    private ResourceLocation bandanaRes;
    private ResourceLocation wingRes;
    private ResourceLocation normalCape;
    private AnimatedResourceLocation animatedCape;




    public CosmeticUser(String _name, String _cape, String _hat, String _bandana, String _wing, String _halo) {
        name = _name;
        setHat(_hat);
        setCape(_cape);
        setBandana(_bandana);
        setWing(_wing);
        setHalo(_halo);
    }


    public String cape() {
        return cape;
    }

    public String halo() {
        return halo;
    }

    public String wing() { return wing;}

    public String bandana() { return bandana; }

    public String hat() {
        return hat;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasCape() { return cape != null && (normalCape != null || animatedCape != null); }
    public boolean hasHat() { return hat != null && hatRes != null;}
    public boolean hasBandana() {return bandana != null && bandanaRes != null;}
    public boolean hasWing() {return wing != null && wingRes != null;}
    public boolean hasHalo() {return halo != null && haloRes != null;}


    public void setHalo(String halo) {
        this.halo = halo;
        if (halo.equalsIgnoreCase("reset")) {
            haloRes = null;
            return;
        }
        ResourceLocation haloInstance = Neko.getInstance().cosmeticTextures.getHalo(halo);
        haloRes = haloInstance;
    }

    public void setHat(String hat) {
        this.hat = hat;
        if (hat.equalsIgnoreCase("reset")) {
            hatRes = null;
            return;
        }
        ResourceLocation hatInstance = Neko.getInstance().cosmeticTextures.getHat(hat);
        hatRes = hatInstance;
    }

    public void setWing(String wing) {
        this.wing = wing;
        if (wing.equalsIgnoreCase("reset")) {
            wingRes = null;
            return;
        }
        ResourceLocation wingInstance = Neko.getInstance().cosmeticTextures.getWing(wing);
        wingRes = wingInstance;
    }

    public void setBandana(String bandana) {
        this.bandana = bandana;
        if (bandana.equalsIgnoreCase("reset")) {
            bandanaRes = null;
            return;
        }
        ResourceLocation bandanaInstance = Neko.getInstance().cosmeticTextures.getBandana(bandana);
        bandanaRes = bandanaInstance;
    }



    public void setCape(String cape) {
        this.cape = cape;
        if (cape.equalsIgnoreCase("reset")) {
            animatedCape = null;
            normalCape = null;
            return;
        }
        Object capeInstance = Neko.getInstance().cosmeticTextures.getCape(cape);
        if (capeInstance instanceof AnimatedResourceLocation) {
            normalCape = null;
            animatedCape = (AnimatedResourceLocation) capeInstance;
        } else if (capeInstance instanceof ResourceLocation) {
            animatedCape = null;
            normalCape = (ResourceLocation) capeInstance;
        }
    }


    public ResourceLocation getCapeRes() {
        if (normalCape != null) {
            return normalCape;
        } else if (animatedCape != null) {
            return animatedCape.getTexture();
        }
        return null;
    }

    public File getFolder() {
        File folder = new File(FileUtils.getCosmeticsDir(), this.getClass().getSimpleName());
        if(!folder.exists()) {
            folder.mkdirs();
        }


        return folder;
    }

    public boolean canRender(AbstractClientPlayer abstractClientPlayer) {
        return Neko.getInstance().getCosmeticMap().containsKey(abstractClientPlayer.getNameClear());
    }



    public String getUpdateText() {
        return NekoSelf.START_PREFIX + name + "::" + cape + "::" + hat + "::" + bandana + "::" + wing + "::" + halo + "::";
    }
}
