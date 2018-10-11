package org.ssunion.cloudschedule.telegram.admin.menus;

import org.ssunion.cloudschedule.domain.Group;
import org.ssunion.cloudschedule.repo.GroupRepo;
import org.ssunion.cloudschedule.telegram.admin.AdminBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupMenu {
    private static GroupRepo gr;
    private static AdminBot adminBot = AdminBot.getInstance();

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
        adminBot.push(message);
    }

    public static void getGroupListByCourse(String course, long chatId) {
        List<Group> groups = gr.findByGroupNameStartingWith(course);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (Group g : groups
        ) {
            keyboard.add(Collections.singletonList(new InlineKeyboardButton().setCallbackData("group_" + g.getGroupName()).setText(g.getGroupName())));
        }
        keyboard.add(Collections.singletonList(new InlineKeyboardButton().setText("Её нет в списке").setCallbackData("nogroup")));
        markup.setKeyboard(keyboard);
        sendMessage.setText("Выберите группу: ");
        adminBot.push(sendMessage);
    }
}
