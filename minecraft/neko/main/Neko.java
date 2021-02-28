package neko.main;

import neko.cmd.CommandManager;
import neko.cosmetic.CosmeticTextures;
import neko.cosmetic.CosmeticUser;
import neko.event.Event;
import neko.event.EventListener;
import neko.event.EventSystem;
import neko.event.RegisterEvent;
import neko.event.impl.LoopEvent;
import neko.gui.hud.HUDManager;
import neko.irc.NekoSelf;
import neko.mod.ModManager;
import neko.utils.FileUtils;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jibble.pircbot.IrcException;
import org.lwjgl.opengl.Display;
import sun.plugin2.message.EventMessage;

import java.io.IOException;
import java.util.HashMap;


public class Neko {

    static Neko INSTANCE;
    public String name = "Neko";
    public ModManager modManager;
    public HUDManager hudManager;
    public CosmeticTextures cosmeticTextures;
    public final Logger logger = LogManager.getLogger();
    private NekoSelf ircSelf;
    private CosmeticUser currentUser;
    private HashMap<String, CosmeticUser> cosmeticMap;

    public static Neko getInstance() {
        return INSTANCE == null ? (INSTANCE = new Neko()) : INSTANCE;
    }

    public void initialize() {
        cosmeticTextures = new CosmeticTextures();
        currentUser = new CosmeticUser(Minecraft.getMinecraft().getSession().getUsername(), "cape", "hat", "bandana", "wing", "halo");
        cosmeticMap = new HashMap<>();
        cosmeticMap.put(Minecraft.getMinecraft().getSession().getUsername(), currentUser);
        ircSelf = new NekoSelf();
        try {
            ircSelf.connect("irc.freenode.net");
        } catch (IOException | IrcException e) {
            e.printStackTrace();
        }
        ircSelf.setVerbose(true);
        ircSelf.joinChannel("#nekomod0");
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                final String tempCape = cosmeticTextures.loadCapesFromFile();
                final String tempHat = cosmeticTextures.loadHatsFromFile();
                final String tempBandana = cosmeticTextures.loadBandanasFromFile();
                final String tempWing = cosmeticTextures.loadWingsFromFile();
                final String tempHalo = cosmeticTextures.loadHalosFromFile();
                currentUser.setCape(tempCape);
                currentUser.setHat(tempHat);
                currentUser.setBandana(tempBandana);
                currentUser.setWing(tempWing);
                currentUser.setHalo(tempHalo);
                updateLook();
            } catch (InterruptedException e) {
            }
            ircSelf.sendMessage("#nekomod0", NekoSelf.START_PREFIX + "getinfo");
        }).start();
        this.logger.info("[" + name + "] " + "client was initialized");
        Display.setTitle("[" + name + "] 1.8.9");
        FileUtils.initialize();
        CommandManager.loadCommands();
        hudManager = HUDManager.getInstance();
        modManager = new ModManager(hudManager);
    }

    public HashMap<String, CosmeticUser> getCosmeticMap() {
        return cosmeticMap;
    }


    public void updateLook() {
        ircSelf.sendMessage("#nekomod0", currentUser.getUpdateText());
    }


    public CosmeticUser registerUser(String user) {
        if (cosmeticMap.containsKey(user)) {
            return cosmeticMap.get(user);
        } else {
            CosmeticUser newUser = new CosmeticUser(user, "cape", "hat", "bandana", "wing", "halo");
            cosmeticMap.put(user, newUser);
            return newUser;
        }
    }


    public CosmeticUser getCosmetic() {
        return currentUser;
    }

    public CosmeticUser getUser(final String name) {
        return registerUser(name);
    }


    public void stop() {}
}
