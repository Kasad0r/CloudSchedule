package org.ssunion.cloudschedule.telegram.admin.controller;

import org.ssunion.cloudschedule.telegram.admin.AdminBot;
import org.ssunion.cloudschedule.telegram.admin.controller.menus.GroupMenu;
import org.ssunion.cloudschedule.telegram.admin.domain.Trigger;
import org.ssunion.cloudschedule.telegram.admin.domain.User;
import org.ssunion.cloudschedule.telegram.admin.repo.UserRepo;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

public class Commands {
    private UserRepo ur;
    private static Commands commands = new Commands();
    private static List<String> commandsList = new ArrayList<>();

    static {
        commandsList.add("/setgroup");
        commandsList.add("/timetopush");
        commandsList.add("/adminnotice");
    }

    private Commands() {

    }

    public void checkCommand(Message message) {
        String command = message.getText();
        if (commandsList.contains(message.getText())) {
            User user = ur.findByUserToken(message.getChatId());
            if (command.equals("/setgroup")) {
                user.setTrigger(Trigger.SET_GROUP_STAGE_1);
                GroupMenu.getStage1(message.getChatId());
                ur.save(user);
            } else if (command.equals("/timetopush")) {
                user.setTrigger(Trigger.SET_TIME_TO_PUSH);

            } else if (command.equals("/adminnotice")) {

            }
        }
    }

    public static Commands getInstance() {
        return commands;
    }
}

