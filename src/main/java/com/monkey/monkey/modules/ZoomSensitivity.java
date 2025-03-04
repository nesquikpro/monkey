package com.monkey.monkey.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ZoomSensitivity
{
    private final Minecraft mc = Minecraft.getMinecraft();
    public static KeyBinding keybindZoom = null;
    private boolean wasSmoothCameraEnabled = false;

    public ZoomSensitivity() {
        zoomKeybind();
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static void zoomKeybind() {
        for (KeyBinding keyBinding : Minecraft.getMinecraft().gameSettings.keyBindings) {
            if (keyBinding.getKeyDescription().toLowerCase().contains("zoom")) {
                keybindZoom = keyBinding;
                break;
            }
        }
    }

    @SubscribeEvent
    public void onCameraSetup(EntityViewRenderEvent.CameraSetup event) {
        if (keybindZoom != null && keybindZoom.isKeyDown()) {
            if (!wasSmoothCameraEnabled) {
                wasSmoothCameraEnabled = mc.gameSettings.smoothCamera;
            }
            mc.gameSettings.smoothCamera = false;
        } else {
            mc.gameSettings.smoothCamera = wasSmoothCameraEnabled;
        }
    }
}
