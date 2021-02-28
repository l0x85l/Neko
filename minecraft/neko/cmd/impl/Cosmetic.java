package neko.cmd.impl;

import neko.cmd.CommandExecutor;
import neko.cosmetic.CosmeticTextures;
import neko.main.Neko;
import neko.utils.PrintUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class Cosmetic implements CommandExecutor {

    @Override
    public void execute(EntityPlayerSP sender, List<String> args) {
        if (args.size() < 1) {
            PrintUtils.error(new ChatComponentText(".cosmetic <name>"));
            PrintUtils.info(new ChatComponentText("type .cosmetic list"));
            return;
        }

        if (args.get(0).contains("list")) {
            PrintUtils.info(new ChatComponentText("CAPES: ahegao, rem"));
            PrintUtils.info(new ChatComponentText("HATS: wizard"));
            PrintUtils.info(new ChatComponentText("HALO: angel"));
            PrintUtils.info(new ChatComponentText("WINGS: dragon"));
            PrintUtils.info(new ChatComponentText("BANDANAS: nike"));
            return;
        }

        Neko.getInstance().updateLook();
        if(args.get(0).contains("cape")) {
            System.out.println(1);
            CosmeticTextures cosmeticTextures = new CosmeticTextures();
            String capeName = args.get(1);
            Neko.getInstance().getCosmetic().setCape(capeName);
            Neko.getInstance().updateLook();
            cosmeticTextures.saveCapesFromFile();
        } else if(args.get(0).contains("halo")) {
            CosmeticTextures cosmeticTextures = new CosmeticTextures();
            String haloName = args.get(1);
            Neko.getInstance().getCosmetic().setHalo(haloName);
            Neko.getInstance().updateLook();
            cosmeticTextures.saveHalosFromFile();
        } else if(args.get(0).contains("hat")) {
            CosmeticTextures cosmeticTextures = new CosmeticTextures();
            String hatName = args.get(1);
            Neko.getInstance().getCosmetic().setHat(hatName);
            Neko.getInstance().updateLook();
            cosmeticTextures.saveHatsFromFile();
        } else if(args.get(0).contains("wing")) {
            CosmeticTextures cosmeticTextures = new CosmeticTextures();
            String wingName = args.get(1);
            Neko.getInstance().getCosmetic().setWing(wingName);
            Neko.getInstance().updateLook();
            cosmeticTextures.saveWingsFromFile();
        } else if(args.get(0).contains("bandana")) {
            CosmeticTextures cosmeticTextures = new CosmeticTextures();
            String bandanaName = args.get(1);
            Neko.getInstance().getCosmetic().setBandana(bandanaName);
            Neko.getInstance().updateLook();
            cosmeticTextures.saveBandanasFromFile();
        }
    }
}
