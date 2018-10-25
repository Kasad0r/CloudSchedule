package org.ssunion.cloudschedule.telegram.adminbot.menus;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

public class StartupMenu {
    public static SendMessage get(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        KeyboardRow row1 = new KeyboardRow();
        row1.add("Активувати");
        sendMessage.setText("<b>Ласкаво просимо</b>");
        sendMessage.setParseMode("html");
        markup.setKeyboard(Collections.singletonList(row1));
        sendMessage.setReplyMarkup(markup);
        return sendMessage;
    }
}
