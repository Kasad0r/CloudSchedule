package org.ssunion.cloudschedule.telegram.admin.logic;

import org.ssunion.cloudschedule.telegram.admin.domain.Trigger;
import org.ssunion.cloudschedule.telegram.admin.domain.User;
import org.ssunion.cloudschedule.telegram.admin.menus.GroupMenu;
import org.ssunion.cloudschedule.telegram.admin.menus.StaticMessages;
import org.ssunion.cloudschedule.telegram.admin.menus.UserStatusMenu;
import org.ssunion.cloudschedule.telegram.admin.repo.UserRepo;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

public final class Commands {
    private UserRepo ur;
    private static Commands commands = new Commands();
    private static List<String> commandsList = new ArrayList<>();

    static {
        commandsList.add("/setgroup");
        commandsList.add("/timetopush");
        commandsList.add("/adminnotice");
        commandsList.add("/status");
    }

    private Commands() {

    }

    public void checkCommand(Message message) {
        String command = message.getText();
        if (commandsList.contains(message.getText())) {
            User user = ur.findByUserToken(message.getChatId());
            if (command.equals("/setgroup")) {
                user.setTrigger(Trigger.SET_GROUP_STAGE_1);
                GroupMenu.executeStage1(message.getChatId());
                ur.save(user);
            } else if (command.equals("/timetopush")) {
                user.setTrigger(Trigger.SET_TIME_TO_PUSH);
            } else if (command.equals("/adminnotice")) {
                if (user.getSettings().isAdminNotice()) {
                    user.getSettings().setAdminNotice(!user.getSettings().isAdminNotice());
                    StaticMessages.createMessage("Сообщения от администрации выключены", message.getChatId());
                    ur.save(user);
                } else {
                    user.getSettings().setAdminNotice(!user.getSettings().isAdminNotice());
                    StaticMessages.createMessage("Сообщения от администрации включены", message.getChatId());
                    ur.save(user);
                }
            } else if (command.equals("/status")) {
                UserStatusMenu.execute(user);
            }
        }
    }

    public static Commands getInstance() {
        return commands;
    }
}

