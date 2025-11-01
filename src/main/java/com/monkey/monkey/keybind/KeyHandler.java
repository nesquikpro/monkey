package com.monkey.monkey.keybind;

import com.monkey.monkey.gui.GuiQuickMod;
import com.monkey.monkey.utils.ServerChecker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;


public class KeyHandler {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static final KeyBinding MURDER_CLASSIC_KEY = new KeyBinding("murder classic",
            Keyboard.KEY_M, "murder mystery");

    public static final KeyBinding OPEN_GUI_KEY = new KeyBinding("open gui",
            Keyboard.KEY_G, "murder mystery");

    public static void register() {
        ClientRegistry.registerKeyBinding(MURDER_CLASSIC_KEY);
        ClientRegistry.registerKeyBinding(OPEN_GUI_KEY);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (!ServerChecker.isOnHypixel()) {
            return;
        }

        if (MURDER_CLASSIC_KEY.isPressed()) {
            mc.thePlayer.sendChatMessage("/play murder_classic");
        } else if (OPEN_GUI_KEY.isPressed()) {
            mc.displayGuiScreen(new GuiQuickMod());
        }
    }
}
