package com.monkey.monkey.gui;

import io.netty.channel.*;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class TabListMod {
    @SubscribeEvent
    public void onClientConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        try {
            injectPacketInterceptor(event.manager);
        } catch (Exception e) {
            System.err.println("[monkey] Error during TabListMod injection: " + e.getMessage());
        }
    }

    private void injectPacketInterceptor(NetworkManager networkManager) {
        ChannelPipeline pipeline = networkManager.channel().pipeline();

        if (pipeline.get("tablist_blocker") == null) {
            pipeline.addBefore("packet_handler", "tablist_blocker",
                    new ChannelInboundHandlerAdapter() {
                @Override
                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                    if (msg instanceof S47PacketPlayerListHeaderFooter) {
                        return;
                    }
                    super.channelRead(ctx, msg);
                }
            });
        }
    }
}
