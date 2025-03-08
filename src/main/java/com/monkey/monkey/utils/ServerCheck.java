package com.monkey.monkey.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;


public class ServerCheck {
    private static boolean isOnHypixel;

    private static Minecraft getMinecraftInstance() {
        return Minecraft.getMinecraft();
    }

    @SubscribeEvent
    public void onConnect(FMLNetworkEvent.ClientConnectedToServerEvent e) {
        final ServerData data = getMinecraftInstance().getCurrentServerData();
        isOnHypixel = data != null && data.serverIP.contains("hypixel.net");
    }

    @SubscribeEvent
    public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
        isOnHypixel = false;
    }

    public static boolean isOnHypixel(){
        return isOnHypixel;
    }
}
