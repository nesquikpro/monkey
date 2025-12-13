package com.monkey.monkey.command;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class FovCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "fov";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/fov";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length != 1) {
            return;
        }

        float fov = Float.parseFloat(args[0]);
            if (fov < 10 )
                fov = 10;
            if (fov > 110)
                fov = 110;

            Minecraft.getMinecraft().gameSettings.fovSetting = fov;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
