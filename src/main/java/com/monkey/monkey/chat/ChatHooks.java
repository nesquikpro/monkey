package com.monkey.monkey.chat;

import net.minecraft.client.gui.FontRenderer;

public class ChatHooks {
    public static int drawBorderedText(FontRenderer fr, String text, float x, float y, int color) {

        int shadowColor = 0x55000000;

        fr.drawString(text, x + 1, y, shadowColor, false);
        fr.drawString(text, x - 1, y, shadowColor, false);
        fr.drawString(text, x, y + 1, shadowColor, false);
        fr.drawString(text, x, y - 1, shadowColor, false);

        return fr.drawString(text, x, y, color, false);
    }
}
