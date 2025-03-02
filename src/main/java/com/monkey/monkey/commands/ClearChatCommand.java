package com.monkey.monkey.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class ClearChatCommand implements ICommand {
    private final List aliases = new ArrayList();

    public ClearChatCommand() {
        this.aliases.add("clearchat");
    }

    public String getCommandName() {
        return "clearchat";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "clearchat";
    }

    public List getCommandAliases() {
        return this.aliases;
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public List addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    public int compareTo(ICommand arg0) {
        return 0;
    }
}
