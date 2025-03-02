package com.monkey.monkey.sidebar;

import com.monkey.monkey.ModManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.ScoreObjective;


public class GuiScreenSidebar extends GuiScreen {
    protected ModManager mod;
    protected GuiSidebar sidebar;

    public GuiScreenSidebar(ModManager mod) {
        this.mod = mod;
        this.sidebar = mod.getSidebarGui();
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (this.mc.thePlayer != null) {
            ScoreObjective scoreObjective = this.mc.thePlayer.getWorldScoreboard().getObjectiveInDisplaySlot(1);
            if (scoreObjective != null)
                this.sidebar.drawSidebar(scoreObjective, new ScaledResolution(this.mc));
        }
    }
}
