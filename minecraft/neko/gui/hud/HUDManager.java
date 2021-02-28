package neko.gui.hud;

import com.google.common.collect.Sets;
import neko.event.Event;
import neko.event.EventListener;
import neko.event.EventSystem;
import neko.event.RegisterEvent;
import neko.event.impl.RenderGuiEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;

import java.util.Collection;
import java.util.Set;

/**
 * @author Eric Golde
 */
public class HUDManager implements EventListener {

    private HUDManager() {}

    private static HUDManager instance = null;

    public static HUDManager getInstance() {

        if(instance != null) {
            return instance;
        }
        instance = new HUDManager();
        EventSystem.register(instance);

        return instance;
    }

    private Set<IRenderer> registeredRenderers = Sets.newHashSet();
    private Minecraft mc = Minecraft.getMinecraft();


    public void register(IRenderer... renderers) {
        for(IRenderer render : renderers) {
            this.registeredRenderers.add(render);
        }
    }

    public void unregister(IRenderer... renderers) {
        for(IRenderer render : renderers) {
            this.registeredRenderers.remove(render);
        }
    }


    public Collection<IRenderer> getRegisteredRenderers() {
        return Sets.newHashSet(registeredRenderers);
    }

    public void openConfigScreen() {
        mc.displayGuiScreen(new HUDConfigScreen(this));
    }



    @Override
    @RegisterEvent(events = {RenderGuiEvent.class})
    public void onEvent(Event event) {
        RenderGuiEvent e = (RenderGuiEvent)event;
        if(!(e instanceof RenderGuiEvent)) {
            return;
        }
        if(mc.currentScreen == null || mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiChat) {
            for(IRenderer renderer : registeredRenderers) {
                callRenderer(renderer);
            }
        }

    }

    private void callRenderer(IRenderer renderer) {
        if(!renderer.isEnabled()) {
            return;
        }

        ScreenPosition pos = renderer.load();

        if(pos == null) {
            pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
        }

        renderer.render(pos);
    }
}
