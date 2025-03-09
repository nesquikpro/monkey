package com.monkey.monkey;

import com.monkey.monkey.chat.BetterChat;
import com.monkey.monkey.commands.*;
import com.monkey.monkey.gui.CrosshairFix;
import com.monkey.monkey.gui.TabListMod;
import com.monkey.monkey.keybinds.SprintHandler;
import com.monkey.monkey.keybinds.KeyHandler;
import com.monkey.monkey.mm.Monkey;
import com.monkey.monkey.modules.BossBarHider;
import com.monkey.monkey.modules.ZoomSensitivity;
import com.monkey.monkey.utils.ServerCheck;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


@Mod(modid = "monkey", version = "1.0")
public class Manager {
    public static final Manager INSTANCE = new Manager();
    private SprintHandler sprintHandler;
    private ZoomSensitivity zoomSensitivity;
    public Manager() {}

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        sprintHandler = new SprintHandler();
        MinecraftForge.EVENT_BUS.register(sprintHandler);
        MinecraftForge.EVENT_BUS.register(new ServerCheck());
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        KeyHandler.register();
        MinecraftForge.EVENT_BUS.register(new Monkey());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        registerEvents();
        registerCommands();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (zoomSensitivity != null) {
            zoomSensitivity.zoomKeybind();
        }
    }

    private void registerCommands() {
        ClientCommandHandler.instance.registerCommand(new CommandSprint(sprintHandler));
        ClientCommandHandler.instance.registerCommand(new CommandCopy());
        ClientCommandHandler.instance.registerCommand(new CommandClear());
        ClientCommandHandler.instance.registerCommand(new CommandMM());
    }

    private void registerEvents() {
        zoomSensitivity = new ZoomSensitivity();
        MinecraftForge.EVENT_BUS.register(zoomSensitivity);
        MinecraftForge.EVENT_BUS.register(new BossBarHider());
        MinecraftForge.EVENT_BUS.register(new CrosshairFix());
        MinecraftForge.EVENT_BUS.register(new BetterChat());
        MinecraftForge.EVENT_BUS.register(new TabListMod());
    }
}

