package neko.cmd.impl;

import neko.cmd.CommandExecutor;
import neko.mod.ModManager;
import neko.utils.PrintUtils;
import net.minecraft.client.entity.EntityPlayerSP;//IDE yi bilmiyon ctrl n yapçağına ctrl nr yapıyon
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class Mods implements CommandExecutor {

    @Override
    public void execute(EntityPlayerSP sender, List<String> args) {
        ModManager.getMods().forEach(mod -> PrintUtils.info(new ChatComponentText(mod.getName().toUpperCase().replace("İ", "I") + " - " + mod.getDesc())));
    }
}
