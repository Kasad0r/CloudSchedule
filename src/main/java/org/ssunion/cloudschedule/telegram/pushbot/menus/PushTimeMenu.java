package org.ssunion.cloudschedule.telegram.pushbot.menus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
public class PushTimeMenu {

    private final PushBot pushBot;

    @Autowired
    public PushTimeMenu(PushBot pushBot) {
        this.pushBot = pushBot;
    }

    public void execute(long chatId) {
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
        pushBot.executeMessage(message);
    }
}
