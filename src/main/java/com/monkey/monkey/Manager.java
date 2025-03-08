package com.monkey.monkey;

import com.monkey.monkey.chat.BetterChat;
import com.monkey.monkey.commands.*;
import com.monkey.monkey.config.ConfigSetup;
import com.monkey.monkey.gui.CrosshairFix;
import com.monkey.monkey.gui.TabListMod;
import com.monkey.monkey.keybinds.SprintHandler;
import com.monkey.monkey.keybinds.KeyHandler;
import com.monkey.monkey.mm.MM;
import com.monkey.monkey.modules.BossBarHider;
import com.monkey.monkey.modules.ZoomSensitivity;
import com.monkey.monkey.sidebar.GuiSidebar;
import com.monkey.monkey.sidebar.GuiSidebarIngame;
import com.monkey.monkey.utils.ServerCheck;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.File;


@Mod(modid = "monkey", version = "1.0")
public class Manager {
    private static final Minecraft mc  = Minecraft.getMinecraft();
    private SprintHandler sprintHandler;
    private ConfigSetup config;
    public GuiSidebar guiSidebar;
    public GuiSidebarIngame ingame;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        sprintHandler = new SprintHandler();
        MinecraftForge.EVENT_BUS.register(sprintHandler);
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        MinecraftForge.EVENT_BUS.register(new ServerCheck());
        KeyHandler.register();
        MinecraftForge.EVENT_BUS.register(new MM());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        registerEvents();
        registerCommands();
    }

    private void registerCommands() {
        ClientCommandHandler.instance.registerCommand(new CommandSprint(sprintHandler));
        ClientCommandHandler.instance.registerCommand(new CommandCopy());
        ClientCommandHandler.instance.registerCommand(new CommandMM());
        ClientCommandHandler.instance.registerCommand(new CommandClear());
    }

    private void registerEvents() {
        this.guiSidebar = new GuiSidebar();
        this.ingame = new GuiSidebarIngame(this, mc);
        setConfig();
        MinecraftForge.EVENT_BUS.register(new ZoomSensitivity());
        MinecraftForge.EVENT_BUS.register(new CrosshairFix());
        MinecraftForge.EVENT_BUS.register(new BetterChat());
        MinecraftForge.EVENT_BUS.register(new TabListMod());
        MinecraftForge.EVENT_BUS.register(new BossBarHider());
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (this.ingame != null && mc.ingameGUI != this.ingame) {
            mc.ingameGUI = this.ingame;
        }
    }

    public GuiSidebar getSidebarGui() {
        return this.guiSidebar;
    }

    private void setConfig(){
        config = new ConfigSetup(this, new File("config/monkey.cfg"));
        config.loadConfig();
    }
}

