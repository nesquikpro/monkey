package com.monkey.monkey;

import com.maxi.mxax.commands.*;
import com.monkey.monkey.commands.ClearChatCommand;
import com.monkey.monkey.commands.CopCommand;
import com.monkey.monkey.commands.MMCommand;
import com.monkey.monkey.commands.SprintCommand;
import com.monkey.monkey.config.ConfigSetup;
import com.maxi.mxax.gui.*;
import com.monkey.monkey.chat.ChatCopyMod;
import com.monkey.monkey.gui.CrosshairFix;
import com.monkey.monkey.gui.QuickMod;
import com.monkey.monkey.gui.TabListMod;
import com.monkey.monkey.keybinds.AutoSprintHandler;
import com.monkey.monkey.keybinds.KeyHandler;
import com.monkey.monkey.mm.monkey;
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
public class ModManager {

    public static final Minecraft mc = Minecraft.getMinecraft();
    private final AutoSprintHandler sprintHandler = new AutoSprintHandler();
    private ConfigSetup config;
    public GuiSidebar guiSidebar;
    public GuiSidebarIngame ingame;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);

        this.guiSidebar = new GuiSidebar();
        this.ingame = new GuiSidebarIngame(this, mc);
        config = new ConfigSetup(this, new File("config/monkey.cfg"));
        config.loadConfig();

        ZoomSensitivity zoom = new ZoomSensitivity();
        ZoomSensitivity.zoomKeybind();
        MinecraftForge.EVENT_BUS.register(zoom);

        MinecraftForge.EVENT_BUS.register(new CrosshairFix());
        MinecraftForge.EVENT_BUS.register(sprintHandler);
        MinecraftForge.EVENT_BUS.register(new ChatCopyMod());
        MinecraftForge.EVENT_BUS.register(new QuickMod());
        MinecraftForge.EVENT_BUS.register(new ZoomSensitivity());
        MinecraftForge.EVENT_BUS.register(new BossBarHider());

        registerCommands();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ServerCheck());
        MinecraftForge.EVENT_BUS.register(new KeyHandler());
        KeyHandler.register();
        MinecraftForge.EVENT_BUS.register(new TabListMod());
        MinecraftForge.EVENT_BUS.register(new monkey());
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (!(mc.ingameGUI instanceof GuiSidebarIngame) && this.ingame != null) {
            mc.ingameGUI = this.ingame;
        }
    }

    private void registerCommands() {
        ClientCommandHandler.instance.registerCommand(new SprintCommand(sprintHandler));
        ClientCommandHandler.instance.registerCommand(new CopCommand());
        ClientCommandHandler.instance.registerCommand(new MMCommand());
        ClientCommandHandler.instance.registerCommand(new ClearChatCommand());
    }

    public GuiSidebar getSidebarGui() {
        return this.guiSidebar;
    }
}

