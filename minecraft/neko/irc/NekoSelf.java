package neko.irc;


import neko.main.Neko;
import net.minecraft.client.Minecraft;
import org.jibble.pircbot.PircBot;

public class NekoSelf extends PircBot {

    public static final String START_PREFIX = "$";

    public NekoSelf() {
        this.setName(Minecraft.getMinecraft().getSession().getUsername() + "_neko");
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        if (message.startsWith(START_PREFIX)) {
            final String full = message.substring(START_PREFIX.length());

            if (full.contains("getinfo")) {
                Neko.getInstance().updateLook();
                return;
            }

            String username = full.split("::")[0];
            String cape = full.split("::")[1];
            String hat = full.split("::")[2];
            String bandana = full.split("::")[3];
            String wing = full.split("::")[4];
            String halo = full.split("::")[5];

            Neko.getInstance().getUser(username).setCape(cape);
            Neko.getInstance().getUser(username).setHat(hat);
            Neko.getInstance().getUser(username).setBandana(bandana);
            Neko.getInstance().getUser(username).setWing(wing);
            Neko.getInstance().getUser(username).setHalo(halo);
        }

    }
}
