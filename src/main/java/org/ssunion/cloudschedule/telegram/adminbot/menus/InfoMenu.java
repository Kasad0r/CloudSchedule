package org.ssunion.cloudschedule.telegram.adminbot.menus;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class InfoMenu {
    public static SendMessage get(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("not done");
        sendMessage.setChatId(chatId);
        sendMessage.setParseMode("html");
        return sendMessage;
    }
}
