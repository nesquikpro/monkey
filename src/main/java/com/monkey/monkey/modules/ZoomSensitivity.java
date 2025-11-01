package com.monkey.monkey.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ZoomSensitivity
{
    private KeyBinding zoomKeybind;
    private boolean initialized = false;

    @SubscribeEvent
    public void onZoom(InputEvent.KeyInputEvent event) {
        if (!initialized) {
            findZoomKeybind();
        }

        if (zoomKeybind != null && zoomKeybind.isKeyDown()) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.gameSettings.smoothCamera = false;
        }
    }

    private void findZoomKeybind() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.gameSettings == null) return;

        for (KeyBinding keyBinding : mc.gameSettings.keyBindings) {
            String desc = keyBinding.getKeyDescription().toLowerCase();
            if (desc.equals("zoom") || desc.equals("of.key.zoom")) {
                zoomKeybind = keyBinding;
                initialized = true;
                return;
            }
        }
    }
}
