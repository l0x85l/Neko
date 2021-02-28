package neko.mod.impl;

import neko.event.Event;
import neko.gui.hud.ScreenPosition;
import neko.mod.ModDraggable;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ArmorStatus extends ModDraggable {


    public ArmorStatus() {
        super("ArmorStatus", "Shows armor status on the screen");
    }

    @Override
    public int getWidth() {
        return 16;
    }

    @Override
    public int getHeight() {
        return 64;
    }

    @Override
    public boolean isEnabled() {
        return getState();
    }

    @Override
    public void render(ScreenPosition pos) {
            for (int i = 0; i < this.mc.thePlayer.inventory.armorInventory.length; ++i)
            {
                ItemStack itemstack = this.mc.thePlayer.inventory.armorInventory[i];
                this.renderItemStack(pos, i, itemstack, 1);
            }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
            ItemStack helmet = new ItemStack(Items.diamond_helmet);
            ItemStack chestplate = new ItemStack(Items.diamond_chestplate);
            ItemStack leggings = new ItemStack(Items.diamond_leggings);
            ItemStack boots = new ItemStack(Items.diamond_boots);
            this.renderItemStack(pos, 3, helmet, 1);
            this.renderItemStack(pos, 2, chestplate, 1);
            this.renderItemStack(pos, 1, leggings, 1);
            this.renderItemStack(pos, 0, boots, 1);
    }

    private void renderItemStack(ScreenPosition pos, int i, ItemStack is, double scaledFactor)
    {
        GlStateManager.pushMatrix();
        GL11.glScaled(scaledFactor, scaledFactor, scaledFactor);
        double newAbsolute = 1.0 / scaledFactor;
        if (is != null) {
            new BlockPos(this.mc.thePlayer.getPosition());

                int yAdd = (16 * i) - 48;
                RenderHelper.enableGUIStandardItemLighting();
                this.mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getAbsoluteX() * newAbsolute, pos.getAbsoluteY() * newAbsolute - yAdd);

        }
        GL11.glScaled(1.0, 1.0, 1.0);
        GlStateManager.popMatrix();
    }

    @Override
    public void onEvent(Event event) {}
}