package org.ssunion.cloudschedule.telegram.pushbot;

import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.telegram.pushbot.logic.controller.MainController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.List;
@Component
public class PushBot extends TelegramLongPollingBot {
    private static PushBot pushBot = new PushBot();
    //@Value("${adminbot.username}")
    private String username="kisit_administration_bot";
    //@Value("${adminbot.token}")
    private String token = "652862402:AAGQFVakTwmj3dtLn-dLlh9qR3xuga8a8WQ";
    private MainController mainController = MainController.getInstance();

    @Override
    public void onUpdateReceived(Update update) {
        mainController.executeUpdate(update);
    }


    public void push(BotApiMethod<Message> message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void push(List<BotApiMethod<Message>> messages) {
        messages.forEach(this::push);
    }

    public void updateMessage(BotApiMethod<Serializable> update) {
        try {
            execute(update);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private PushBot() {

    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public static PushBot getInstance() {
        return pushBot;
    }
}
