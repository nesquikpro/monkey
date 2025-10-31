package com.monkey.monkey.command;

import com.monkey.monkey.mm.MurderMystery;
import com.monkey.monkey.utils.ServerCheck;
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
        if (!ServerCheck.isOnHypixel()) {
            return;
        }

        MurderMystery.isMonkey = !MurderMystery.isMonkey;
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED
                + (MurderMystery.isMonkey ? "1" : "0")));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
