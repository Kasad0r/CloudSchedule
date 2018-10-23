package org.ssunion.cloudschedule.telegram.adminbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.telegram.adminbot.controller.AdminBotController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

/**
 * @author kasad0r
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AdminBot extends TelegramLongPollingBot {
    @Value("${adminbot.username}")
    String username;
    @Value("${adminbot.token}")
    String token;

    private AdminBotController adminBotController;

    @Autowired
    public void setAdminBotController(AdminBotController adminBotController) {
        this.adminBotController = adminBotController;
    }

    @Override
    public void onUpdateReceived(Update update) {
        adminBotController.performUpdate(update);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public void executeMessage(BotApiMethod<Message> sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void updateMessage(EditMessageText editMessageText) {
        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void executeMessage(List<SendMessage> sendMessages) {
        sendMessages.forEach(this::executeMessage);
    }

    public void executeDocument(SendDocument sendDocument) {
        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
