package com.monkey.monkey.mm;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

public class Monkey {
    private static final Set<String> murders = new HashSet<>();
    private static final Set<String> highlightedPlayers = new HashSet<>();

    public static boolean isMonkey = false;
    private static boolean isClassic = false;
    private static boolean isDouble = false;
    private World lastWorld = null;

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (!isMonkey || (!isClassic && !isDouble)) return;
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer thePlayer = mc.thePlayer;
        for (String playerName : getOnlinePlayersByName()) {
            if (playerName.equals(thePlayer.getName())) continue;
            EntityPlayer player = mc.theWorld.getPlayerEntityByName(playerName);
            if (player == null || player.getHeldItem() == null) continue;
            if (KnifeSkins.isKfSk(player.getHeldItem().getItem()) && murders.add(playerName)) {
                highlightedPlayers.add(playerName);
                highlightPlayerInTab(playerName, EnumChatFormatting.RED);
                thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + playerName));
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (!isMonkey || event.phase != TickEvent.Phase.END) return;
        World world = Minecraft.getMinecraft().theWorld;
        if (world != this.lastWorld) {
            clearAll();
        }
        this.lastWorld = world;
        if (world == null || Minecraft.getMinecraft().getNetHandler() == null) return;
        for (String playerName : highlightedPlayers) {
            highlightPlayerInTab(playerName, EnumChatFormatting.DARK_RED);
        }
    }

    public static List<String> getOnlinePlayersByName() {
        Collection<NetworkPlayerInfo> players = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
        List<String> playerNames = new ArrayList<>(players.size());
        for (NetworkPlayerInfo playerInfo : players) {
            playerNames.add(playerInfo.getGameProfile().getName());
        }
        return playerNames;
    }

    public static void highlightPlayerInTab(String playerName, EnumChatFormatting color) {
        if (Minecraft.getMinecraft().getNetHandler() == null) return;
        Collection<NetworkPlayerInfo> players = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
        if (players == null) return;
        for (NetworkPlayerInfo playerInfo : players) {
            if (playerInfo.getGameProfile().getName().equals(playerName)) {
                playerInfo.setDisplayName(new ChatComponentText(color + playerName));
                break;
            }
        }
    }

    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event){
        if (!isMonkey) return;
        String message = event.message.getUnformattedText();
        if (message.equals("Teaming with the Murderer is not allowed!") || message.equals("Teaming with the Detective/Innocents is not allowed!")) {
            isClassic = true;
            isDouble = false;
        } else if (message.equals("Teaming with the Murderers is not allowed!")) {
            isDouble = true;
            isClassic = false;
        } else if (message.contains("Winner:")) {
            clearAll();
        }
    }

    public void clearAll() {
        murders.clear();
        highlightedPlayers.clear();
        isClassic = false;
        isDouble = false;
    }
}