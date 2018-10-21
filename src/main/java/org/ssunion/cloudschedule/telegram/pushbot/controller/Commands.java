package org.ssunion.cloudschedule.telegram.pushbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.domain.telegram.pushbot.Trigger;
import org.ssunion.cloudschedule.domain.telegram.pushbot.User;
import org.ssunion.cloudschedule.service.impl.UserServiceImpl;
import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.ssunion.cloudschedule.telegram.pushbot.menus.GroupMenu;
import org.ssunion.cloudschedule.telegram.pushbot.menus.UserStatusMenu;
import org.ssunion.cloudschedule.telegram.pushbot.messages.MessageFactory;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

@Component
public final class Commands {
    private final UserServiceImpl userService;
    private final GroupMenu groupMenu;
    private static List<String> commandsList = new ArrayList<>();
    private final UserStatusMenu userStatusMenu;
    private final PushBot pushBot;

    static {
        commandsList.add("/setgroup");
        commandsList.add("/timetopush");
        commandsList.add("/adminnotice");
        commandsList.add("/status");
    }

    @Autowired
    public Commands(UserServiceImpl userService, GroupMenu groupMenu, UserStatusMenu userStatusMenu, PushBot pushBot) {
        this.userService = userService;
        this.groupMenu = groupMenu;
        this.userStatusMenu = userStatusMenu;
        this.pushBot = pushBot;
    }


    public void checkCommand(Message message) {
        String command = message.getText();
        if (commandsList.contains(message.getText())) {
            User user = userService.getUserByToken(message.getChatId());
            switch (command) {
                case "/setgroup":
                    user.setTrigger(Trigger.SET_GROUP_STAGE_1);
                    groupMenu.executeStage1(message.getChatId());
                    userService.addUser(user);
                    break;
                case "/timetopush":
                    user.setTrigger(Trigger.SET_TIME_TO_PUSH);
                    break;
                case "/adminnotice":
                    if (user.getSettings().isAdminNotice()) {
                        user.getSettings().setAdminNotice(!user.getSettings().isAdminNotice());
                        pushBot.executeMessage(MessageFactory.create(message.getChatId(), "Сообщения от администрации выключены"));
                        userService.addUser(user);
                    } else {
                        user.getSettings().setAdminNotice(!user.getSettings().isAdminNotice());
                        pushBot.executeMessage(MessageFactory.create(message.getChatId(), "Сообщения от администрации включены"));
                        userService.addUser(user);
                    }
                    break;
                case "/status":
                    userStatusMenu.execute(user);
                    break;
            }
        }
    }
}

