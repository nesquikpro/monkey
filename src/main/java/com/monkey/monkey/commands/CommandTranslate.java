package com.monkey.monkey.commands;

import com.monkey.monkey.chat.BetterChat;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandTranslate extends CommandBase {
    @Override
    public String getCommandName() {
        return "t";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/t";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) return;

        final String text = String.join(" ", args);

        new Thread(() -> {
            String translatedLocal = BetterChat.translateSync(text);

            if (translatedLocal != null && !translatedLocal.isEmpty()) {
                if (translatedLocal.endsWith(".")) {
                    translatedLocal = translatedLocal.substring(0, translatedLocal.length() - 1);
                }

                final String finalTranslated = translatedLocal.toLowerCase();

                Minecraft.getMinecraft().addScheduledTask(() -> {
                    BetterChat.sendChatMessage(finalTranslated);
                });
            }
        }).start();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
