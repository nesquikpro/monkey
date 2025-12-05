package com.monkey.monkey.mm;

import com.monkey.monkey.enums.GameMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

public class MurderMystery {

    public static boolean isMonkey = false;

    private GameMode gameMode = GameMode.NONE;
    private World lastWorld = null;

    private final Set<String> murders = new HashSet<>();
    private final Set<String> highlightedPlayers = new HashSet<>();
    private final Map<String, EnumChatFormatting> tabColors = new HashMap<>();
    private final Map<String, IChatComponent> originalNames = new HashMap<>();

    private List<String> cachedPlayers = new ArrayList<>();
    private int playerListUpdateTicks = 0;

    // Частота обновления списка игроков. 20 тиков ~= 1 секунда.
    private static final int PLAYER_REFRESH_TICKS = 20;

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (!isMonkey || gameMode == GameMode.NONE) return;

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP self = mc.thePlayer;
        if (self == null || mc.theWorld == null) return;

        for (String name : cachedPlayers) {
            if (name == null) continue;
            if (name.equalsIgnoreCase(self.getName())) continue;

            EntityPlayer target = mc.theWorld.getPlayerEntityByName(name);
            if (target == null) continue;

            ItemStack held = target.getHeldItem();
            if (held == null || held.getItem() == null) continue;

            if (KnifeSkins.isKnifeSkin(held.getItem())) {
                markAsMurderer(name);
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

        if (++playerListUpdateTicks >= PLAYER_REFRESH_TICKS) {
            cachedPlayers = getOnlinePlayers();
            playerListUpdateTicks = 0;
        }

        if (mc.getNetHandler() != null) {
            for (String name : new HashSet<>(highlightedPlayers)) {
                if (name == null) continue;
                highlightPlayerInTab(name, EnumChatFormatting.DARK_RED);
            }
        }
    }

    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event) {
        if (!isMonkey || event.message == null) return;

        String message = event.message.getUnformattedText();
        if (message == null) return;
        message = message.trim();

        if (message.equalsIgnoreCase("Teaming with the Murderer is not allowed!")
                || message.equalsIgnoreCase("Teaming with the Detective/Innocents is not allowed!")) {
            gameMode = GameMode.CLASSIC;
        } else if (message.equalsIgnoreCase("Teaming with the Murderers is not allowed!")) {
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
            if (info == null || info.getGameProfile() == null) continue;
            String name = info.getGameProfile().getName();
            if (name != null) names.add(name);
        }
        return names;
    }

    private void markAsMurderer(String name) {
        if (name == null) return;
        String nameLower = name.toLowerCase(Locale.ROOT);

        if (!murders.contains(nameLower)) {
            murders.add(nameLower);
            highlightedPlayers.add(nameLower);

            highlightPlayerInTab(name, EnumChatFormatting.DARK_RED);

            Minecraft mc = Minecraft.getMinecraft();
            if (mc.thePlayer != null) {
                mc.thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + name + " is a murderer"));
            }
        } else {
            highlightedPlayers.add(nameLower);
        }
    }

    private void highlightPlayerInTab(String name, EnumChatFormatting color) {
        if (name == null || color == null) return;

        Minecraft mc = Minecraft.getMinecraft();
        if (mc.getNetHandler() == null) return;

        String nameLower = name.toLowerCase(Locale.ROOT);

        for (NetworkPlayerInfo info : mc.getNetHandler().getPlayerInfoMap()) {
            if (info == null || info.getGameProfile() == null) continue;
            String profileName = info.getGameProfile().getName();
            if (profileName == null) continue;

            if (profileName.equalsIgnoreCase(name)) {
                if (!originalNames.containsKey(nameLower)) {
                    IChatComponent original = info.getDisplayName();
                    if (original != null) {
                        originalNames.put(nameLower, original.createCopy());
                    } else {
                        originalNames.put(nameLower, new ChatComponentText(profileName));
                    }
                }

                info.setDisplayName(new ChatComponentText(color + profileName));
                tabColors.put(nameLower, color);
                return;
            }
        }
    }

    private void restoreOriginalTabNames() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.getNetHandler() == null) {
            originalNames.clear();
            return;
        }

        for (NetworkPlayerInfo info : mc.getNetHandler().getPlayerInfoMap()) {
            if (info == null || info.getGameProfile() == null) continue;
            String profileName = info.getGameProfile().getName();
            if (profileName == null) continue;

            String keyLower = profileName.toLowerCase(Locale.ROOT);
            if (originalNames.containsKey(keyLower)) {
                IChatComponent original = originalNames.get(keyLower);
                if (original != null) {
                    info.setDisplayName(original);
                } else {
                    info.setDisplayName(new ChatComponentText(profileName));
                }
            }
        }

        originalNames.clear();
    }

    private void clearAll() {
        murders.clear();
        highlightedPlayers.clear();
        tabColors.clear();
        gameMode = GameMode.NONE;

        restoreOriginalTabNames();
    }
}
