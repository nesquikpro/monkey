package com.monkey.monkey.mixins.chat;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GuiChat.class)
public abstract class GuiChatMixin {
    @Shadow
    private GuiTextField inputField;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void updateScreen() {
        String message = inputField.getText();
        if (message.contains("<3")) {
            message = message.replace("<3", "❤");
            inputField.setText(message);
            inputField.setCursorPosition(message.length());
        }
    }
}
