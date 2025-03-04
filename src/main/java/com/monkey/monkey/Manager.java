package com.monkey.monkey;

import com.monkey.monkey.commands.CommandClear;
import com.monkey.monkey.commands.CommandCopy;
import com.monkey.monkey.commands.CommandMM;
import com.monkey.monkey.commands.CommandSprint;
import com.monkey.monkey.config.ConfigSetup;
import com.monkey.monkey.chat.CopyMod;
import com.monkey.monkey.gui.CrosshairFix;
import com.monkey.monkey.gui.QuickMod;
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

    public static final Minecraft mc = Minecraft.getMinecraft();
    private final SprintHandler sprintHandler = new SprintHandler();
    private ConfigSetup config;
    public GuiSidebar guiSidebar;
    public GuiSidebarIngame ingame;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        registerEvents();
        registerCommands();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ServerCheck());
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        KeyHandler.register();
        MinecraftForge.EVENT_BUS.register(new TabListMod());
        MinecraftForge.EVENT_BUS.register(new MM());
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (!(mc.ingameGUI instanceof GuiSidebarIngame) && this.ingame != null) {
            mc.ingameGUI = this.ingame;
        }
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
        config = new ConfigSetup(this, new File("config/monkey.cfg"));
        config.loadConfig();
        MinecraftForge.EVENT_BUS.register(new ZoomSensitivity());
        MinecraftForge.EVENT_BUS.register(new CrosshairFix());
        MinecraftForge.EVENT_BUS.register(sprintHandler);
        MinecraftForge.EVENT_BUS.register(new CopyMod());
        MinecraftForge.EVENT_BUS.register(new QuickMod());
        MinecraftForge.EVENT_BUS.register(new ZoomSensitivity());
        MinecraftForge.EVENT_BUS.register(new BossBarHider());
    }

    public GuiSidebar getSidebarGui() {
        return this.guiSidebar;
    }
}

