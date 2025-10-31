package com.monkey.monkey.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class CommandCopy extends CommandBase {
    @Override
    public String getCommandName() {
        return "copy";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/copy";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            return;
        }

        String textToCopy = String.join(" ", args);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(textToCopy), null);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
