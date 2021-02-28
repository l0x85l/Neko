package neko.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.GL_SCISSOR_TEST;

public class MultipleGLScissor
{
    private static final int max = 100;
    private static MultipleGLScissor[] objects = new MultipleGLScissor[max];
    private static int lastObject = -1;

    private int index;

    private int left;
    private int right;
    private int top;
    private int bottom;

    public MultipleGLScissor(int x, int y, int width, int height)
    {
        lastObject++;
        if (lastObject < max)
        {
            index = lastObject;
            objects[index] = this;

            left = x;
            right = x + width - 1;
            top = y;
            bottom = y + height - 1;

            if (index > 0)
            {
                MultipleGLScissor parent = objects[index - 1];

                if (left < parent.left) left = parent.left;
                if (right > parent.right) right = parent.right;
                if (top < parent.top) top = parent.top;
                if (bottom > parent.bottom) bottom = parent.bottom;
            }

            resume();
        }
        else
        {
            System.out.println("Scissor count limit reached: " + max);
        }
    }

    private void resume()
    {
        glScissor(left, top, right - left + 1, bottom - top + 1);
        glEnable(GL_SCISSOR_TEST);
    }

    public void destroy()
    {
        if (index < lastObject)
        {
            System.out.println("There are scissors below this one");
        }

        glDisable(GL_SCISSOR_TEST);

        objects[index] = null;
        lastObject--;

        if (lastObject > -1)
            objects[lastObject].resume(); // Resuming previous scissor
    }

    protected void finalize()
    {
        destroy();
    }

    private void glScissor(double x, double y, double width, double height) {

        y += height;

        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());

        Minecraft mc = Minecraft.getMinecraft();

        GL11.glScissor((int) ((x * mc.displayWidth) / scaledResolution.getScaledWidth()),
                (int) (((scaledResolution.getScaledHeight() - y) * mc.displayHeight) / scaledResolution.getScaledHeight()),
                (int) (width * mc.displayWidth / scaledResolution.getScaledWidth()),
                (int) (height * mc.displayHeight / scaledResolution.getScaledHeight()));
    }
}