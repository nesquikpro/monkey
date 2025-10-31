package com.monkey.monkey.keybind;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class SprintHandler {
    private static final Minecraft mc = Minecraft.getMinecraft();

    private boolean autoSprintEnabled = true;

    public void toggleSprint() {
        autoSprintEnabled = !autoSprintEnabled;

        if (!autoSprintEnabled) {
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(),
                    false);
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START ||
                !event.player.worldObj.isRemote) return;

        if (autoSprintEnabled) {
            KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(),
                    true);
        }
    }
}
