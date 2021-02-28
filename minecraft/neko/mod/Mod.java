package neko.mod;

import neko.event.EventListener;
import neko.utils.FileUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.io.File;

public abstract class Mod implements EventListener {
    protected String mod_name;
    protected String mod_desc;
    protected FontRenderer font;
    protected boolean mod_state;
    protected Minecraft mc;


    public Mod(String mod_name, String mod_desc) {
        this.mod_name = mod_name;
        this.mod_desc = mod_desc;
        this.mc = Minecraft.getMinecraft();
        this.font = mc.fontRendererObj;

        final File enabled = new File(getFolder(), "enabled.json");

        if (!enabled.exists()) {
            saveEnabledFromFile();

            this.mod_state = false;
        }

        this.mod_state = loadEnabledFromFile();
    }

    public File getFolder() {
        File folder = new File(FileUtils.getModsDir(), this.getClass().getSimpleName());
        if(!folder.exists()) {
            folder.mkdirs();
        }


        return folder;
    }

    public void saveEnabledFromFile(){
        FileUtils.writeJsonToFile(new File(getFolder(), "enabled.json"), getState());
    }

    public boolean loadEnabledFromFile(){
        return FileUtils.readFromJson(new File(getFolder(), "enabled.json"), Boolean.class);
    }



    public String getName() {
        return mod_name;
    }

    public void setName(String mod_name) {
        this.mod_name = mod_name;
    }

    public String getDesc() {
        return mod_desc;
    }

    public void setDesc(String mod_desc) {
        this.mod_desc = mod_desc;
    }

    public boolean getState() {
        return mod_state;
    }

    public void setState(boolean moduleState) {
        this.mod_state = moduleState;
        if (moduleState) {
            this.onEnabled();
        } else {
            this.onDisabled();
        }
    }

    public abstract void onEnabled();

    public abstract void onDisabled();

}
