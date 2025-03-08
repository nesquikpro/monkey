package com.monkey.monkey.commands;

import com.monkey.monkey.keybinds.SprintHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandSprint extends CommandBase {
    private final SprintHandler sprintHandler;

    public CommandSprint(SprintHandler sprintHandler) {
        this.sprintHandler = sprintHandler;
    }

    @Override
    public String getCommandName() {
        return "ts";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/ts";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        sprintHandler.toggleSprint();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
