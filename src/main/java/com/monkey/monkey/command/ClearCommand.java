package com.monkey.monkey.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class ClearCommand extends CommandBase {

    public String getCommandName() {
        return "cc";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/cc";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
