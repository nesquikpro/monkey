package com.monkey.monkey.modules;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BossBarHider
{
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent e){
        if(e.type == RenderGameOverlayEvent.ElementType.BOSSHEALTH){
            e.setCanceled(true);
        }
    }
}
