package org.ssunion.cloudschedule.telegram.admin.menus;

import org.ssunion.cloudschedule.telegram.admin.AdminBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupMenu {
    public static void executeStage1(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Выберите ваш курс: ");
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardList = new ArrayList<>();
        keyboardList.add(Collections.singletonList(new InlineKeyboardButton("1").setCallbackData("course_1")));
        keyboardList.add(Collections.singletonList(new InlineKeyboardButton("2").setCallbackData("course_2")));
        keyboardList.add(Collections.singletonList(new InlineKeyboardButton("3").setCallbackData("course_3")));
        keyboardList.add(Collections.singletonList(new InlineKeyboardButton("4").setCallbackData("course_4")));
        keyboardMarkup.setKeyboard(keyboardList);
        message.disableNotification();
        message.setReplyMarkup(keyboardMarkup);
        AdminBot.getInstance().push(message);
    }
}
