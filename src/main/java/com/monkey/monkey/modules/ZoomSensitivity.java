package com.monkey.monkey.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class ZoomSensitivity
{
    public static KeyBinding keybindZoom = null;

    public ZoomSensitivity(){
        zoomKeybind();
    }
    @SubscribeEvent
    public void onZoom(InputEvent.KeyInputEvent event) {
        if (keybindZoom != null && keybindZoom.isKeyDown()) {
            Minecraft.getMinecraft().gameSettings.smoothCamera = false;
        }
    }

    public void zoomKeybind() {
        KeyBinding[] k = Minecraft.getMinecraft().gameSettings.keyBindings;
        for (KeyBinding keyBinding : k) {
            if (keyBinding.getKeyDescription().equalsIgnoreCase("Zoom") || keyBinding.getKeyDescription().equalsIgnoreCase("of.key.zoom")) {
                keybindZoom = keyBinding;
                return;
            }
        }
    }
}
