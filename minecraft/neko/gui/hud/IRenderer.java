package neko.gui.hud;

/**
 * @author Eric Golde
 */
public interface IRenderer extends IRenderConfig {

    int getWidth();
    int getHeight();
    boolean isEnabled();

    void render(ScreenPosition pos);

    default void renderDummy(ScreenPosition pos) {
        render(pos);
    }

}

