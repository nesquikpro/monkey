package com.monkey.monkey.modules;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BossBarHider
{
    public BossBarHider() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent e){
        if(e.type == RenderGameOverlayEvent.ElementType.BOSSHEALTH){
            e.setCanceled(true);
        }
    }
}
