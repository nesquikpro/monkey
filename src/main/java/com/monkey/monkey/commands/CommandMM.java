package com.monkey.monkey.commands;

import com.monkey.monkey.mm.MM;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandMM extends CommandBase {
    @Override
    public String getCommandName() {
        return "monkey";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/monkey";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + getMonkey()));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public static String getMonkey() {
        MM.isMonkey = !MM.isMonkey;
        return MM.isMonkey ? "monkey" : "";
    }
}
