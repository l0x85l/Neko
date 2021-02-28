package neko.mod;

import neko.event.EventSystem;
import neko.gui.hud.IRenderer;
import neko.gui.hud.ScreenPosition;
import neko.utils.FileUtils;

import java.io.File;

public abstract class ModDraggable extends Mod implements IRenderer {

    protected ScreenPosition pos;

    public ModDraggable(String mod_name, String mod_desc) {
        super(mod_name, mod_desc);
        this.pos = loadPositionFromFile();
    }

    @Override
    public void onEnabled() {
        saveEnabledFromFile();
        EventSystem.register(this);
    }

    @Override
    public void onDisabled() {
        saveEnabledFromFile();
        EventSystem.unregister(this);
    }


    @Override
    public ScreenPosition load() {
        return pos;
    }

    @Override
    public void save(ScreenPosition pos) {
        this.pos = pos;
        savePositionFromFile();
    }


    private void savePositionFromFile() {
        FileUtils.writeJsonToFile(new File(getFolder(), "positions.json"), pos);
    }



    private ScreenPosition loadPositionFromFile() {
        ScreenPosition loaded = FileUtils.readFromJson(new File(getFolder(), "positions.json"), ScreenPosition.class);

        if(loaded == null) {
            loaded = ScreenPosition.fromRelativePosition(0.5, 0.5);
            this.pos = loaded;
            savePositionFromFile();
        }

        return loaded;
    }


    public final int getLineOffset(ScreenPosition pos, int lineNum) {
        return pos.getAbsoluteX() + getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum) {
        return (font.FONT_HEIGHT + 3) * lineNum;
    }
}
