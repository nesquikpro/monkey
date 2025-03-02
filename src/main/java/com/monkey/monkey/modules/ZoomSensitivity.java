package com.monkey.monkey.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class ZoomSensitivity
{
    public static KeyBinding keybindZoom = null;

    @SubscribeEvent
    public void onZoom(InputEvent.KeyInputEvent e){
        if(keybindZoom!= null && keybindZoom.isPressed()){
            Minecraft.getMinecraft().gameSettings.smoothCamera = false;
        }
    }

    public static void zoomKeybind() {
        for (KeyBinding keyBinding : Minecraft.getMinecraft().gameSettings.keyBindings) {
            if (keyBinding.getKeyDescription().toLowerCase().contains("zoom")) {
                keybindZoom = keyBinding;
                break;
            }
        }
    }
}
