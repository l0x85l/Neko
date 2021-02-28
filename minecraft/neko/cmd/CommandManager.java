package neko.cmd;

import neko.cmd.impl.*;
import neko.main.Neko;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private static final List<Command> commands = new ArrayList<>();

    private static final String prefix = ".";

    public static void loadCommands() {
        commands.add(new Command("help", "list of all commands", new Help()));
        commands.add(new Command("mod", "mod management command", new Mod()));
        commands.add(new Command("mods", "list of all mods", new Mods()));
        commands.add(new Command("cosmetic", "get best cosmetics", new Cosmetic()));
        Neko.getInstance().logger.info("[" + Neko.getInstance().name + "] " + commands.size() + " cmd loaded");

    }

    public static List<String> getArgs(String text) {
        if (!isCommand(text))
            return new ArrayList<>();

        final List<String> args = new ArrayList<>();

        final String[] split = seperatePrefix(text).split(" ");

        int beginIndex = 1;

        for (int i = beginIndex; i < split.length; i++){
            final String arg = split[i];

            if (arg == null)
                continue;

            args.add(arg);
        }

        return args;
    }

    public static Command findCommand(String text) {
        final String[] split = seperatePrefix(text).split(" ");

        if (split.length <= 0)
            return null;


        return commands.stream()
                .filter(cmd -> cmd.getName().equalsIgnoreCase(split[0]))
                .findFirst()
                .orElse(null);
    }

    public static String seperatePrefix(String text) {
        if (!text.startsWith(prefix))
            return prefix + text;

        return text.substring(1);
    }

    public static boolean isCommand(String text){
        return findCommand(text) != null;
    }

    public static List<Command> getCommands() {
        return commands;
    }
}
