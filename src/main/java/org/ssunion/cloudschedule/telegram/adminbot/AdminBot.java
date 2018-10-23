package org.ssunion.cloudschedule.telegram.adminbot;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author kasad0r
 */
public class AdminBot extends TelegramLongPollingBot {
    @Value("${adminbot.username}")
    String username;
    @Value("${adminbot.token")
    String token;

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
