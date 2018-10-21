package org.ssunion.cloudschedule.telegram.pushbot.menus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.domain.base.Group;
import org.ssunion.cloudschedule.service.impl.GroupServiceImpl;
import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kasad0r
 */
@Component
public class GroupMenu {
    private static GroupServiceImpl groupService;
    private static PushBot pushBot;

    @Autowired
    public GroupMenu(GroupServiceImpl groupService, PushBot pushBot) {
        GroupMenu.groupService = groupService;
        GroupMenu.pushBot = pushBot;
    }

    public void executeStage1(long chatId) {
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
        pushBot.executeMessage(message);
    }

    public void getGroupListByCourse(String course, long chatId) {
        List<Group> groups = groupService.getByCource(course);
        System.out.println(groups);
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
        sendMessage.setReplyMarkup(markup);
        sendMessage.setText("Выберите группу: ");
        pushBot.executeMessage(sendMessage);
    }
}
