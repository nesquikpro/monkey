package com.monkey.monkey.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class CopCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "cop";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/cop";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            return;
        }

        String textToCopy = String.join(" ", args);
        StringSelection selection = new StringSelection(textToCopy);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
