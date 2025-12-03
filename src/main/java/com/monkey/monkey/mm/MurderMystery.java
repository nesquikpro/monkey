package com.monkey.monkey.mm;

import com.monkey.monkey.enums.GameMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

public class MurderMystery {

    private static final Set<String> murders = new HashSet<>();
    private static final Set<String> highlightedPlayers = new HashSet<>();
    private static final Map<String, EnumChatFormatting> tabColors = new HashMap<>();

    public static boolean isMonkey = false;
    private GameMode gameMode = GameMode.NONE;
    private World lastWorld = null;

    private List<String> cachedPlayers = new ArrayList<>();
    private int playerListUpdateTicks = 0;

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (!isMonkey || gameMode == GameMode.NONE) return;

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        if (player == null || mc.theWorld == null) return;

        for (String name : cachedPlayers) {
            if (name.equals(player.getName())) continue;

            EntityPlayer target = mc.theWorld.getPlayerEntityByName(name);
            if (target == null) continue;

            ItemStack held = target.getHeldItem();
            if (held == null || held.getItem() == null) continue;

            if (KnifeSkins.isKnifeSkin(held.getItem()) && murders.add(name)) {
                highlightedPlayers.add(name);
                highlightPlayerInTab(name, EnumChatFormatting.DARK_RED);
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + name));
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (!isMonkey || event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.theWorld;

        if (!Objects.equals(world, lastWorld)) {
            clearAll();
        }
        lastWorld = world;

        if (++playerListUpdateTicks >= 20) {
            cachedPlayers = getOnlinePlayers();
            playerListUpdateTicks = 0;
        }

        for (String name : highlightedPlayers) {
            highlightPlayerInTab(name, EnumChatFormatting.DARK_RED);
        }
    }

    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {
        if (!isMonkey || event.message == null) return;

        String message = event.message.getUnformattedText();
        if (message == null) return;

        message = message.trim();

        if (message.equalsIgnoreCase(
                "Teaming with the Murderer is not allowed!")
                || message.equalsIgnoreCase(
                        "Teaming with the Detective/Innocents is not allowed!")) {
            gameMode = GameMode.CLASSIC;
        } else if (message.equalsIgnoreCase(
                "Teaming with the Murderers is not allowed!")) {
            gameMode = GameMode.DOUBLE;
        } else if (message.startsWith("Winner:")) {
            clearAll();
        }
    }

    private List<String> getOnlinePlayers() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.getNetHandler() == null) return Collections.emptyList();

        List<String> names = new ArrayList<>();
        for (NetworkPlayerInfo info : mc.getNetHandler().getPlayerInfoMap()) {
            names.add(info.getGameProfile().getName());
        }
        return names;
    }

    private void highlightPlayerInTab(String name, EnumChatFormatting color) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.getNetHandler() == null) return;

        if (color.equals(tabColors.get(name))) return;

        for (NetworkPlayerInfo info : mc.getNetHandler().getPlayerInfoMap()) {
            if (info.getGameProfile().getName().equals(name)) {
                info.setDisplayName(new ChatComponentText(color + name));
                tabColors.put(name, color);
                break;
            }
        }
    }

    private void clearAll() {
        murders.clear();
        highlightedPlayers.clear();
        tabColors.clear();
        gameMode = GameMode.NONE;
    }
}
