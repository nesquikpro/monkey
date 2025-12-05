package com.monkey.monkey.mixins.render;

import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiIngame.class)
public abstract class MixinGuiInGame {
    @Redirect(method = "renderScoreboard",
    at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;append(I)Ljava/lang/StringBuilder;"))
    private StringBuilder removeScoreNumbers(StringBuilder sb, int value) {
        return sb.append("");
    }
}
