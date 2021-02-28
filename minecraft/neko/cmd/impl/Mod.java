package neko.cmd.impl;

import neko.cmd.CommandExecutor;
import neko.mod.ModManager;
import neko.utils.PrintUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class Mod implements CommandExecutor {

    @Override
    public void execute(EntityPlayerSP sender, List<String> args) {

        if (args.size() < 1) {
            PrintUtils.error(new ChatComponentText(".mod <modname> enable/disable"));
            return;
        }

        if (args.size() == 1){
            PrintUtils.error(new ChatComponentText(".mod <modname> enable/disable"));
            return;
        }

        final String modName = args.get(0);

        if (ModManager.getMod(modName) == null){
            PrintUtils.warning(new ChatComponentText("unknown or undefined mod"));
            return;
        }
        if (args.get(1).equalsIgnoreCase("enable")){
            ModManager.getMod(modName).setState(true);
            PrintUtils.info(new ChatComponentText(ModManager.getMod(modName).getName() + " was enabled"));
        } else if (args.get(1).equalsIgnoreCase("disable")) {
            ModManager.getMod(modName).setState(false);
            PrintUtils.info(new ChatComponentText(ModManager.getMod(modName).getName() + " was disabled"));
        }
    }
}
