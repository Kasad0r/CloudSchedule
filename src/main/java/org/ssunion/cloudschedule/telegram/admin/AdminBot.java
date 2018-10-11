package org.ssunion.cloudschedule.telegram.admin;

import org.springframework.beans.factory.annotation.Value;
import org.ssunion.cloudschedule.telegram.admin.logic.controller.MainController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.List;

public class AdminBot extends TelegramLongPollingBot {
    private static AdminBot adminBot = new AdminBot();
    @Value("${adminbot.username}")
    private String username;
    @Value("${adminbot.token}")
    private String token;
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

    private AdminBot() {

    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public static AdminBot getInstance() {
        return adminBot;
    }
}
