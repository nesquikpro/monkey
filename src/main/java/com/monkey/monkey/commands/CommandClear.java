package com.monkey.monkey.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class CommandClear extends CommandBase {

    public String getCommandName() {
        return "clear";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/clear";
    }

    public void processCommand(ICommandSender sender, String[] args){
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
