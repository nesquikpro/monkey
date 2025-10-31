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
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

public class MurderMystery {

    private static final Set<String> murders = new HashSet<>();
    private static final Set<String> highlightedPlayers = new HashSet<>();

    public static boolean isMonkey = false;

    private GameMode gameMode = GameMode.NONE;
    private World lastWorld = null;

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (!isMonkey || gameMode == GameMode.NONE) return;

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        if (player == null || mc.theWorld == null) return;

        for (String name : getOnlinePlayers()) {
            if (name.equals(player.getName())) continue;

            EntityPlayer target = mc.theWorld.getPlayerEntityByName(name);
            if (target == null) continue;

            ItemStack held = target.getHeldItem();
            if (held == null || held.getItem() == null) continue;

            if (KnifeSkins.isKnifeSkin(held.getItem()) && murders.add(name)) {
                highlightedPlayers.add(name);
                highlightPlayerInTab(name, EnumChatFormatting.RED);
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

        for (String name : highlightedPlayers) {
            highlightPlayerInTab(name, EnumChatFormatting.DARK_RED);
        }
    }

    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {
        if (!isMonkey) return;

        if (event.message == null) return; // NPE

        String message = event.message.getUnformattedText();
        if (message == null) return;

        message = message.trim();

        if (message.equals("Teaming with the Murderer is not allowed!")
                || message.equals("Teaming with the Detective/Innocents is not allowed!")) {
            gameMode = GameMode.CLASSIC;
        } else if (message.equals("Teaming with the Murderers is not allowed!")) {
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

        for (NetworkPlayerInfo info : mc.getNetHandler().getPlayerInfoMap()) {
            if (info.getGameProfile().getName().equals(name)) {
                info.setDisplayName(new ChatComponentText(color + name));
                break;
            }
        }
    }

    private void clearAll() {
        murders.clear();
        highlightedPlayers.clear();
        gameMode = GameMode.NONE;
    }
}
