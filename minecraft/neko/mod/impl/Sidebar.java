package neko.mod.impl;


import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import neko.event.Event;
import neko.gui.hud.ScreenPosition;
import neko.mod.ModDraggable;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.*;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Sidebar extends ModDraggable {
    private int sWidth;
    private int sHeight;

    public Sidebar() {
        super("Sidebar", "Shows sidebar on the screen");
    }

    @Override
    public int getWidth() {
        return sWidth + 1;
    }

    @Override
    public int getHeight() {
        return sHeight;
    }

    @Override
    public boolean isEnabled() {
        return getState();
    }

    @Override
    public void render(ScreenPosition pos) {
        Scoreboard scoreboard = this.mc.theWorld.getScoreboard();
        ScoreObjective scoreobjective = null;
        ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(this.mc.thePlayer.getName());
        ScaledResolution scaledresolution = new ScaledResolution(mc);

        if (scoreplayerteam != null)
        {
            int i1 = scoreplayerteam.getChatFormat().getColorIndex();

            if (i1 >= 0)
            {
                scoreobjective = scoreboard.getObjectiveInDisplaySlot(3 + i1);
            }
        }

        ScoreObjective scoreobjective1 = scoreobjective != null ? scoreobjective : scoreboard.getObjectiveInDisplaySlot(1);

        if (scoreobjective1 != null)
        {
            this.renderScoreboard(scoreobjective1, scaledresolution);
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        Scoreboard scoreboard = this.mc.theWorld.getScoreboard();
        ScoreObjective scoreobjective = null;
        ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(this.mc.thePlayer.getName());
        ScaledResolution scaledresolution = new ScaledResolution(mc);

        if (scoreplayerteam != null)
        {
            int i1 = scoreplayerteam.getChatFormat().getColorIndex();

            if (i1 >= 0)
            {
                scoreobjective = scoreboard.getObjectiveInDisplaySlot(3 + i1);
            }
        }

        ScoreObjective scoreobjective1 = scoreobjective != null ? scoreobjective : scoreboard.getObjectiveInDisplaySlot(1);

        if (scoreobjective1 != null)
        {
            this.renderScoreboard(scoreobjective1, scaledresolution);
        }
    }


    private void renderScoreboard(final ScoreObjective objective, final ScaledResolution resolution) {
        GL11.glPushMatrix();
        Scoreboard scoreboard = objective.getScoreboard();
        Collection<Score> collection = scoreboard.getSortedScores(objective);
        List<Score> list = Lists.newArrayList(Iterables.filter(collection, p_apply_1_ -> p_apply_1_.getPlayerName() != null && !p_apply_1_.getPlayerName().startsWith("#")));

        if (list.size() > 15)
        {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        }
        else
        {
            collection = list;
        }
        int i = font.getStringWidth(objective.getDisplayName());
        GL11.glTranslatef(1,collection.size() * font.FONT_HEIGHT + 10,1);
        for (Score score : collection)
        {
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName()) + ": " + EnumChatFormatting.RED + score.getScorePoints();
            i = Math.max(i, font.getStringWidth(s));
        }
        this.sHeight = collection.size() * font.FONT_HEIGHT + font.FONT_HEIGHT + 2;
        this.sWidth = i;
        int j = 0;
        for (Score score1 : collection)
        {
            ++j;
            ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
            String s1 = ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());
            String s2 = EnumChatFormatting.RED + "" + score1.getScorePoints();
            int k = pos.getAbsoluteY() - j * font.FONT_HEIGHT;
            Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() - j * font.FONT_HEIGHT, pos.getAbsoluteX() + i, (pos.getAbsoluteY() - (j + 1) * font.FONT_HEIGHT), 1342177280);
            font.drawString(s1, this.pos.getAbsoluteX() + 1, k + 1, 553648127);
            font.drawString(s2, this.pos.getAbsoluteX() + i - font.getStringWidth(s2), k + 1, 553648127);
            if (j == collection.size())
            {
                String s3 = objective.getDisplayName();
                Gui.drawRect(pos.getAbsoluteX(), pos.getAbsoluteY() +1 , (pos.getAbsoluteX() + i), pos.getAbsoluteY() - font.FONT_HEIGHT, 1342177280);
                font.drawString(s3, pos.getAbsoluteX() + i / 2 - font.getStringWidth(s3) / 2 + 1, (k - 8), 553648127);
            }
        }
        GL11.glPopMatrix();
    }



    @Override
    public void onEvent(Event event) {}
}