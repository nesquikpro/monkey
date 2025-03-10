package com.monkey.monkey.mixins.chat;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiChat.class)
public abstract class GuiChatMixin {
    @Shadow protected GuiTextField inputField;

    @Inject(method = "keyTyped", at = @At("HEAD"))
    private void onKeyTyped(char typedChar, int keyCode, CallbackInfo ci) {
        if (inputField != null) {
            String text = inputField.getText();
            if (text.contains("<3")) {
                inputField.setText(text.replace("<3", "❤"));
                inputField.setCursorPosition(text.length());
            }
        }
    }
}
