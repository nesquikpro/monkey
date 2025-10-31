package com.monkey.monkey;

import com.monkey.monkey.command.*;
import com.monkey.monkey.gui.TabListMod;
import com.monkey.monkey.keybind.SprintHandler;
import com.monkey.monkey.keybind.KeyHandler;
import com.monkey.monkey.mm.MurderMystery;
import com.monkey.monkey.moduls.BossBarHider;
import com.monkey.monkey.moduls.ZoomSensitivity;
import com.monkey.monkey.utils.ServerCheck;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "monkey", version = "1.0")
public final class Manager {
    @Mod.Instance
    public static Manager instance;

    private SprintHandler sprintHandler;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        sprintHandler = new SprintHandler();

        MinecraftForge.EVENT_BUS.register(sprintHandler);
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        KeyHandler.register();
        MinecraftForge.EVENT_BUS.register(new ServerCheck());
        MinecraftForge.EVENT_BUS.register(new MurderMystery());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        registerEvents();
        registerCommands();
    }

    private void registerCommands() {
        ClientCommandHandler.instance.registerCommand(new CommandSprint(sprintHandler));
        ClientCommandHandler.instance.registerCommand(new CommandCopy());
        ClientCommandHandler.instance.registerCommand(new CommandClear());
        ClientCommandHandler.instance.registerCommand(new CommandMM());
    }

    private void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new BossBarHider());
        MinecraftForge.EVENT_BUS.register(new TabListMod());
        MinecraftForge.EVENT_BUS.register(new ZoomSensitivity());
    }
}

