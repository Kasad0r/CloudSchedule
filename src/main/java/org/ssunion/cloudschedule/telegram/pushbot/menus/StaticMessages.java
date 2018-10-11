package org.ssunion.cloudschedule.telegram.pushbot.menus;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StaticMessages {
    public static SendMessage createMessage(String text, long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        return message;
    }

}
