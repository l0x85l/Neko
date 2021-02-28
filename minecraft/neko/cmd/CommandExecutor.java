package neko.cmd;

import net.minecraft.client.entity.EntityPlayerSP;

import java.util.List;

public interface CommandExecutor {

    void execute(EntityPlayerSP sender, List<String> args);
}
