package org.ssunion.cloudschedule.telegram.pushbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.domain.telegram.pushbot.Trigger;
import org.ssunion.cloudschedule.domain.telegram.pushbot.User;
import org.ssunion.cloudschedule.service.impl.GroupServiceImpl;
import org.ssunion.cloudschedule.service.impl.UserServiceImpl;
import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.ssunion.cloudschedule.telegram.pushbot.menus.GroupMenu;
import org.ssunion.cloudschedule.telegram.pushbot.menus.PushTimeMenu;
import org.ssunion.cloudschedule.telegram.pushbot.menus.UserStatusMenu;
import org.ssunion.cloudschedule.telegram.pushbot.messages.MessageFactory;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kasad0r
 */
@Component
public final class Commands {
    private final UserServiceImpl userService;
    private final GroupMenu groupMenu;
    private static List<String> commandsList = new ArrayList<>();
    private final UserStatusMenu userStatusMenu;
    private final PushBot pushBot;
    private final PushTimeMenu pushTimeMenu;
    private final GroupServiceImpl groupService;

    static {
        commandsList.add("/setgroup");
        commandsList.add("/timetopush");
        commandsList.add("/adminnotice");
        commandsList.add("/status");
        commandsList.add("/week");
        commandsList.add("/help");
        commandsList.add("now");
        commandsList.add("/next");
        commandsList.add("/prev");
    }

    @Autowired
    public Commands(UserServiceImpl userService, GroupMenu groupMenu, UserStatusMenu userStatusMenu, PushBot pushBot, PushTimeMenu pushTimeMenu, GroupServiceImpl groupService) {
        this.userService = userService;
        this.groupMenu = groupMenu;
        this.userStatusMenu = userStatusMenu;
        this.pushBot = pushBot;
        this.pushTimeMenu = pushTimeMenu;
        this.groupService = groupService;
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
                    pushTimeMenu.execute(message.getChatId());
                    userService.addUser(user);
                    break;
                case "/adminnotice":
                    if (user.getSettings().isAdminNotice()) {
                        user.getSettings().setAdminNotice(!user.getSettings().isAdminNotice());
                        pushBot.executeMessage(MessageFactory.createBold(message.getChatId(), "Сообщения от администрации выключены"));
                        userService.addUser(user);
                    } else {
                        user.getSettings().setAdminNotice(!user.getSettings().isAdminNotice());
                        pushBot.executeMessage(MessageFactory.createBold(message.getChatId(), "Сообщения от администрации включены"));
                        userService.addUser(user);
                    }
                    break;
                case "/status":
                    userStatusMenu.execute(user);
                    break;
                case "/help":
                    pushBot.executeMessage(MessageFactory.createBold(message.getChatId(),
                            "/setgroup - выбор группы\n" +
                                    "/timetopush - время отправки рассписания\n" +
                                    "/adminnotice - вкл сообщения от администрации\n" +
                                    "/status - инфо об аккаунте\n" +
                                    "/week - рассписание на неделю \n" +
                                    "/now - рассписание на сегодня"));

                case "/week":

                    pushBot.executeMessage(
                            MessageFactory.
                                    createBold(user.getUserToken(),
                                            groupService.getByName(user.getSettings().getSelectedGroup())
                                                    .getScheduleForTelegram()));

                    break;
                default:
                    break;
            }
        }
    }
}

