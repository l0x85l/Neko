package neko.cmd.impl;

import neko.cmd.CommandExecutor;
import neko.cmd.CommandManager;
import neko.utils.PrintUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class Help implements CommandExecutor {

    @Override
    public void execute(EntityPlayerSP sender, List<String> args) {
        CommandManager.getCommands().forEach(command -> PrintUtils.info(new ChatComponentText(command.getName().toUpperCase() + " - " + command.getDesc())));
    }
}
