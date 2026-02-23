package com.monkey.monkey.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class TranslateCommand extends CommandBase {

    // cache
    private static final Map<String, String> cache = new LinkedHashMap<String, String>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > 100;
        }
    };

    @Override
    public String getCommandName() {
        return "tr";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/tr";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText(
                    EnumChatFormatting.RED + "use: /tr"
            ));
            return;
        }

        // Собираем весь текст из аргументов
        final String text = String.join(" ", args);

        // Если фраза уже переводилась — показываем мгновенно из кэша
        if (cache.containsKey(text.toLowerCase())) {
            String cached = cache.get(text.toLowerCase());
            sender.addChatMessage(buildClickableMessage(cached, true));
            return;
        }

        // Перевод выполняем в отдельном потоке, чтобы не блокировать игру
        new Thread(() -> {
            try {
                String translated = translate(text);
                String lower = translated.isEmpty() ? translated
                        : Character.toLowerCase(translated.charAt(0)) + translated.substring(1);
                cache.put(text.toLowerCase(), lower);

                // Возвращаем результат в основной поток
                MinecraftServer server = MinecraftServer.getServer();
                if (server != null) {
                    server.addScheduledTask(() -> {
                        sender.addChatMessage(buildClickableMessage(lower, false));
                    });
                }
            } catch (Exception e) {
                MinecraftServer server = MinecraftServer.getServer();
                if (server != null) {
                    server.addScheduledTask(() -> sender.addChatMessage(
                            new ChatComponentText(
                                    EnumChatFormatting.RED + "Ошибка перевода: " + e.getMessage()
                            )
                    ));
                }
            }
        }, "TranslatorThread").start();
    }

    /**
     * Переводит текст с русского на английский через MyMemory API (бесплатный, без ключа).
     * URL: https://api.mymemory.translated.net/get?q=TEXT&langpair=ru|en
     */
    private String translate(String text) throws Exception {
        String encodedText = URLEncoder.encode(text, "UTF-8");
        String urlStr = "https://api.mymemory.translated.net/get?q=" + encodedText + "&langpair=ru|en";

        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(3000);
        connection.setRequestProperty("User-Agent", "MinecraftTranslatorMod/1.0");

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new Exception("HTTP error: " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Парсим JSON вручную (без сторонних библиотек)
        // Ищем "translatedText":"..."
        String json = response.toString();
        String key = "\"translatedText\":\"";
        int start = json.indexOf(key);
        if (start == -1) {
            throw new Exception("Unable to decipher the response API");
        }
        start += key.length();
        int end = json.indexOf("\"", start);
        if (end == -1) {
            throw new Exception("Unable to decipher the response API");
        }

        return json.substring(start, end);
    }

    private ChatComponentText buildClickableMessage(String translated, boolean fromCache) {
        ChatComponentText prefix = new ChatComponentText(EnumChatFormatting.DARK_RED + "[EN]: ");

        ChatComponentText translatedPart = new ChatComponentText(EnumChatFormatting.WHITE + translated);
        translatedPart.setChatStyle(new ChatStyle()
                .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/copy " + translated))
        );

        prefix.appendSibling(translatedPart);

        if (fromCache) {
            prefix.appendSibling(new ChatComponentText(EnumChatFormatting.DARK_GRAY + " (cache)"));
        }

        return prefix;
    }
}
