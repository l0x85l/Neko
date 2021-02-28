package neko.gui.hud;

/**
 * @author Eric Golde
 */
public interface IRenderConfig {

    public void save(ScreenPosition pos);

    public ScreenPosition load();
}
