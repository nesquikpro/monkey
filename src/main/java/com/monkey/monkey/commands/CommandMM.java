package com.monkey.monkey.commands;

import com.monkey.monkey.mm.Monkey;
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
        sender.addChatMessage(new ChatComponentText(getMonkey()));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public static String getMonkey() {
        Monkey.isMonkey = !Monkey.isMonkey;
        return Monkey.isMonkey ? EnumChatFormatting.DARK_RED + "monkey" : EnumChatFormatting.BLACK+ "monkey";
    }
}
