package com.monkey.monkey.keybinds;

import com.monkey.monkey.gui.QuickMod;
import com.monkey.monkey.utils.ServerCheck;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import static com.monkey.monkey.ModManager.mc;

public class KeyHandler {
    public static final KeyBinding murderClassic = new KeyBinding("murder mystery", Keyboard.KEY_ADD, "monkey");
    public static final KeyBinding openGuiKey = new KeyBinding("monkey", Keyboard.KEY_G, "monkey");

    public static void register() {
        ClientRegistry.registerKeyBinding(openGuiKey);
        ClientRegistry.registerKeyBinding(murderClassic);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (murderClassic.isPressed() && ServerCheck.isOnHypixel()) {
            if (mc.thePlayer != null) {
                mc.thePlayer.sendChatMessage("/play murder_Classic");
            }
        }
        if (openGuiKey.isPressed()) {
            mc.displayGuiScreen(new QuickMod());
        }
    }
}
