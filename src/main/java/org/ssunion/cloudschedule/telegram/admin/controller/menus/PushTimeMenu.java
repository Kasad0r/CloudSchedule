package org.ssunion.cloudschedule.telegram.admin.controller.menus;

import org.ssunion.cloudschedule.telegram.admin.AdminBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PushTimeMenu {
    public static void get(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите время для отправки расписания: ");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardList = new ArrayList<>();
        keyboardList.add(Collections.singletonList(new InlineKeyboardButton("18:00").setCallbackData("time_18")));
        keyboardList.add(Collections.singletonList(new InlineKeyboardButton("19:00").setCallbackData("time_19")));
        keyboardList.add(Collections.singletonList(new InlineKeyboardButton("20:00").setCallbackData("time_20")));
        keyboardList.add(Collections.singletonList(new InlineKeyboardButton("21:00").setCallbackData("time_21")));

        keyboardMarkup.setKeyboard(keyboardList);
        message.disableNotification();
        message.setReplyMarkup(keyboardMarkup);
        AdminBot.getInstance().push(message);
    }
}
