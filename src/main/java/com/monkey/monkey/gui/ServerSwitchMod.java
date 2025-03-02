package com.monkey.monkey.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class ServerSwitchMod extends GuiMultiplayer {

    private final Minecraft mc = Minecraft.getMinecraft();

    public ServerSwitchMod(GuiScreen parent) {
        super(parent);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1 || button.id == 4)
            disconnect();
        super.actionPerformed(button);
    }
    @Override
    public void confirmClicked(boolean result, int id) {
        if (id == 0 && result) {
            disconnect();
        }
        super.confirmClicked(result, id);
    }

    private void disconnect() {
        if (mc.theWorld != null) {
            mc.theWorld.sendQuittingDisconnectingPacket();
            mc.loadWorld(null);
            mc.displayGuiScreen(null);
        }
    }
}
