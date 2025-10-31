package com.monkey.monkey.gui;

import com.monkey.monkey.utils.ServerCheck;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

public class GuiQuickMod extends GuiScreen {
    private static final int BUTTON_DOUBLE_UP = 0;
    private static final int BUTTON_CLASSIC = 1;
    @Override
    public void initGui() {
        int centerX = width / 2;
        int centerY = height / 2;
        int buttonWidth = 120;
        int buttonHeight = 20;
        int spacing = 5;

        buttonList.add(new GuiButton(BUTTON_DOUBLE_UP, centerX - buttonWidth / 2,
                centerY - (buttonHeight * 2 + spacing * 2),
                buttonWidth, buttonHeight, EnumChatFormatting.DARK_RED + "mm 2"));
        buttonList.add(new GuiButton(BUTTON_CLASSIC, centerX - buttonWidth / 2,
                centerY - (buttonHeight + spacing),
                buttonWidth, buttonHeight, EnumChatFormatting.DARK_RED + "mm 1"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        Minecraft mc = Minecraft.getMinecraft();

        if(ServerCheck.isOnHypixel()){
            switch (button.id) {
                case BUTTON_DOUBLE_UP:
                    mc.thePlayer.sendChatMessage("/play murder_double_up");
                    break;
                case BUTTON_CLASSIC:
                    mc.thePlayer.sendChatMessage("/play murder_classic");
                    break;
            }
        }

        mc.displayGuiScreen(null);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        drawCenteredString(fontRendererObj, EnumChatFormatting.DARK_RED + "monkey", width / 2,
                height / 2 - 80, 0xFFFFFF);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}