package org.ssunion.cloudschedule.telegram.adminbot.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.telegram.adminbot.AdminBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;

/**
 * @author kasad0r
 */
@Component
public class SendAdminMessageFactory {
    private static AdminBot adminBot;

    public static void getTypeOfAdminMessage(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Виберіть важливість повідомлення ");
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        row1.add("Важливе");
        row2.add("Звичайне");
        sendMessage.setReplyMarkup(new ReplyKeyboardMarkup().setKeyboard(Arrays.asList(row1, row2)));
        adminBot.executeMessage(sendMessage);
    }

    @Autowired
    public SendAdminMessageFactory(AdminBot adminBot) {
        SendAdminMessageFactory.adminBot = adminBot;
    }
}
