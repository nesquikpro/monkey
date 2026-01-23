package com.monkey.monkey.commands;

import com.monkey.monkey.keybinds.SprintHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class SprintCommand extends CommandBase {
    private final SprintHandler sprintHandler;

    public SprintCommand(SprintHandler sprintHandler) {
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
