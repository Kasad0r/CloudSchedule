package org.ssunion.cloudschedule.telegram.adminbot.menus;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;

public class MainMenu {
    public static SendMessage get(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Головне меню:");
        sendMessage.setParseMode("html");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        KeyboardRow row1 = new KeyboardRow();
        row1.add("Оновити розклад");
        KeyboardRow row2 = new KeyboardRow();
        row2.add("Створити повідомлення");
        KeyboardRow row3 = new KeyboardRow();
        row3.add("Сгенерувати код");
        KeyboardRow row4 = new KeyboardRow();
        row4.add("Інфо");
        replyKeyboardMarkup.setKeyboard(Arrays.asList(row1, row2, row3, row4));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
}