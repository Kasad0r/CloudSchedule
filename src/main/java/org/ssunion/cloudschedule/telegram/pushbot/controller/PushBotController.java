package org.ssunion.cloudschedule.telegram.pushbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.domain.telegram.pushbot.User;
import org.ssunion.cloudschedule.service.impl.UserServiceImpl;
import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.ssunion.cloudschedule.telegram.pushbot.messages.MessageFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component("PushBotMainController")
public class PushBotController {
    private final UserServiceImpl userService;

    private final PushBot pushBot;

    private final Commands commands;

    private final Trigges trigges;

    @Autowired
    public PushBotController(UserServiceImpl userService, PushBot pushBot, Commands commands, Trigges trigges) {
        this.userService = userService;
        this.pushBot = pushBot;
        this.commands = commands;
        this.trigges = trigges;
    }


    public void performUpdate(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();
                if (messageText != null && !messageText.isEmpty()) {
                    if (messageText.equals("/start")) {
                        createUser(update.getMessage());
                        pushBot.executeMessage(MessageFactory.create(chatId, "Добро пожаловать, вы были зарегистрированы"));
                    }
                    if (userService.checkUser(chatId)) {
                        commands.checkCommand(update.getMessage());
                    }
                }
            } else {

            }
        } else if (update.hasCallbackQuery()) {
            trigges.check(update);
        }
    }

    private void createUser(Message message) {
        User user = new User();
        user.setUsername(message.getFrom().getUserName());
        user.setFirstname(message.getFrom().getFirstName());
        user.setLastname(message.getFrom().getLastName());
        user.setUserToken(message.getChatId());
        user.getSettings();
        userService.addUser(user);
    }
}
