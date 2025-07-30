package com.monkey.monkey.gui;

import com.monkey.monkey.utils.ServerCheck;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;

public class QuickMod extends GuiScreen {
    @Override
    public void initGui() {
        int centerX = width / 2;
        int centerY = height / 2;
        int buttonWidth = 100;
        int buttonHeight = 20;
        int spacing = 5;

        buttonList.add(new GuiButton(0, centerX - buttonWidth / 2, centerY - (buttonHeight * 2 + spacing * 2), buttonWidth, buttonHeight, EnumChatFormatting.DARK_RED + "mm 2"));
        buttonList.add(new GuiButton(1, centerX - buttonWidth / 2, centerY - (buttonHeight + spacing), buttonWidth, buttonHeight, EnumChatFormatting.DARK_RED + "mm 1"));
        buttonList.add(new GuiButton(2, centerX - buttonWidth / 2, centerY, buttonWidth, buttonHeight, EnumChatFormatting.DARK_RED + "monkey"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        Minecraft mc = Minecraft.getMinecraft();
        if(ServerCheck.isOnHypixel()){
            switch (button.id) {
                case 0:
                    mc.thePlayer.sendChatMessage("/play murder_double_up");
                    break;
                case 1:
                    mc.thePlayer.sendChatMessage("/play murder_classic");
                    break;
                case 2:
                    ClientCommandHandler.instance.executeCommand(mc.thePlayer, "/monkey");
                    break;
            }
        }
        mc.displayGuiScreen(null);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, EnumChatFormatting.DARK_RED + "monkey", width / 2, height / 2 - 80, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}