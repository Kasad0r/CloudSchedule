package org.ssunion.cloudschedule.telegram.pushbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.telegram.pushbot.controller.PushBotController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class PushBot extends TelegramLongPollingBot {

    @Value("${adminbot.username}")
    private String username;
    @Value("${adminbot.token}")
    private String token;
    private static PushBot pushBot = new PushBot();
    @Autowired
    private  PushBotController pushBotController;

    private PushBot() {
    }


    public static PushBot getInstance() {
        return pushBot;
    }

    @Override
    public void onUpdateReceived(Update update) {
        pushBotController.performUpdate(update);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public void executeMessage(BotApiMethod<Message> sendMessage)  {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
