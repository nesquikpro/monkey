package com.monkey.monkey.mixins.world;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Pseudo
@Mixin(targets = "net.optifine.CustomSky", remap = false)
public abstract class CustomSkyMixin {
    @ModifyVariable(method = "renderSky", at = @At("STORE"), ordinal = 0)
    private static long changeWorldTime(long time) {
        return 18000L;
    }
}
