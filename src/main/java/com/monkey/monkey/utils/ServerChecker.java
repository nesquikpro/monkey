package com.monkey.monkey.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;


public class ServerChecker {
    private static boolean isOnHypixel = false;

    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        ServerData data = mc.getCurrentServerData();

        if (data != null && data.serverIP != null) {
            isOnHypixel = data.serverIP.toLowerCase().contains("hypixel.net");
        } else {
            isOnHypixel = false;
        }
    }

    @SubscribeEvent
    public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        isOnHypixel = false;
    }

    public static boolean isOnHypixel() {
        return isOnHypixel;
    }
}
