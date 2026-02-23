package com.monkey.monkey.chat;

import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatMod {
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        if (event.type == 2) {
            return;
        }

        String unformattedText = StringUtils.stripControlCodes(event.message.getFormattedText());

        if (!unformattedText.replace(" ", "").isEmpty()) {
            ChatStyle chatStyle = new ChatStyle().setChatClickEvent(new ClickEvent(
                    ClickEvent.Action.RUN_COMMAND, "/copy " + unformattedText));
            event.message.setChatStyle(chatStyle);
        }
    }
}
