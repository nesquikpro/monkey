package com.monkey.monkey.commands;

import com.monkey.monkey.mm.MurderMystery;
import com.monkey.monkey.utils.ServerChecker;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class AutoMonkeyCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "automonkey";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/automonkey";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!ServerChecker.isOnHypixel()) {
            return;
        }

        MurderMystery.isAutoMonkey = !MurderMystery.isAutoMonkey;
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED
                + (MurderMystery.isAutoMonkey ? "1" : "0")));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
