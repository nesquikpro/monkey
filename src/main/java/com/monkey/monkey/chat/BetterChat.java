package com.monkey.monkey.chat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import okhttp3.*;

import java.io.IOException;


public class BetterChat {
    private static final OkHttpClient client = new OkHttpClient();
    private static final String LOCAL_DEEPLX_URL = "http://127.0.0.1:1188/translate";

    @SubscribeEvent()
    public void onChatReceived(ClientChatReceivedEvent e) {
        if (e.type == 2) return;
        String unformattedText = StringUtils.stripControlCodes(e.message.getUnformattedText());
        if (!unformattedText.replace(" ", "").isEmpty()) {
            ChatComponentText copyText = new ChatComponentText(EnumChatFormatting.DARK_RED + "[M]");
            ChatStyle style = new ChatStyle()
                    .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/copy " + unformattedText));
            copyText.setChatStyle(style);
            e.message.appendSibling(new ChatComponentText(EnumChatFormatting.RESET + " "));
            e.message.appendSibling(copyText);
        }
    }


    public static String translateSync(String text) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String jsonBody = "{\"text\":\"" + escapeJson(text) + "\",\"source_lang\":\"RU\",\"target_lang\":\"EN\"}";

        Request request = new Request.Builder()
                .url(LOCAL_DEEPLX_URL)
                .post(RequestBody.create(JSON, jsonBody))
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) return null;

            String body = response.body().string();
            int start = body.indexOf("\"data\":\"") + 8;
            int end = body.indexOf("\"", start);
            if (start > 7 && end > start) {
                return body.substring(start, end);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void sendChatMessage(String message) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        if (player != null) {
            player.sendChatMessage(message);
        }
    }

    private static String escapeJson(String text) {
        return text.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}

//https://www.deepl.com/ru/translator#ru/en-us/