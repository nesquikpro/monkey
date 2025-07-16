package com.monkey.monkey.keybinds;

import com.monkey.monkey.gui.QuickMod;
import com.monkey.monkey.utils.ServerCheck;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;


public class KeyHandler {
    public static final KeyBinding murderClassic = new KeyBinding("murder classic", Keyboard.KEY_ADD, "murder mystery");
    public static final KeyBinding openGuiKey = new KeyBinding("monkey", Keyboard.KEY_G, "murder mystery");

    public static void register() {
        ClientRegistry.registerKeyBinding(murderClassic);
        ClientRegistry.registerKeyBinding(openGuiKey);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if(ServerCheck.isOnHypixel()){
            if (murderClassic.isPressed()) {
                mc.thePlayer.sendChatMessage("/play murder_Classic");
            }
            if (openGuiKey.isPressed()) {
                mc.displayGuiScreen(new QuickMod());
            }
        }
    }
}
