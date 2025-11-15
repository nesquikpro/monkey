package com.monkey.monkey.mixins.render;

import com.monkey.monkey.chat.ChatHooks;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiNewChat.class)
public class GuiNewChatMixin {
    @Redirect(method = "drawChat", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/FontRenderer;drawStringWithShadow(Ljava/lang/String;FFI)I")
    )
    private int chat$textShadow(FontRenderer fr, String text, float x, float y, int color) {
        return ChatHooks.drawBorderedText(fr, text, x, y, color);
    }
}
