package com.monkey.monkey.mixins.render;

import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(World.class)
public class WorldMixin {
    @Shadow
    @Final
    public boolean isRemote;

    @Redirect(method = {"getMoonPhase", "getCelestialAngle"},
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/storage/WorldInfo;getWorldTime()J"))

    private long setTimeForMoonPhase(WorldInfo worldInfo) {
        return this.isRemote ? 18000L : worldInfo.getWorldTime();
    }
}

