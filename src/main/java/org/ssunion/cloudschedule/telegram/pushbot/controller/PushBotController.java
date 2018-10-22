package org.ssunion.cloudschedule.telegram.pushbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.domain.base.Group;
import org.ssunion.cloudschedule.domain.telegram.pushbot.User;
import org.ssunion.cloudschedule.service.impl.UserServiceImpl;
import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.ssunion.cloudschedule.telegram.pushbot.messages.MessageFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

/**
 * @author kasad0r
 */
@Component("PushBotMainController")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PushBotController {
    private final UserServiceImpl userService;

    private PushBot pushBot;

    private Commands commands;

    private Trigges trigges;

    @Autowired
    public PushBotController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPushBot(PushBot pushBot) {
        this.pushBot = pushBot;
    }

    @Autowired
    public void setTrigges(Trigges trigges) {
        this.trigges = trigges;
    }

    @Autowired
    public void setCommands(Commands commands) {
        this.commands = commands;
    }

    public void performUpdate(Update update) {
        System.out.println(userService.getUsersBySelectedGroupAndNotice("152", true));
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                long chatId = update.getMessage().getChatId();
                if (messageText != null && !messageText.isEmpty()) {
                    if ("/start".equals(messageText)) {
                        createUser(update.getMessage());
                        pushBot.executeMessage(MessageFactory.createBold(chatId, "Добро пожаловать, вы были зарегистрированы"));
                    }
                    if (userService.checkUser(chatId)) {
                        commands.checkCommand(update.getMessage());
                    }
                }
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

    //TODO

    /**
     * @param groups
     */
    public void pushScheduleByGroup(List<Group> groups) {
        for (Group group : groups) {
            pushScheduleByGroup(group);
        }
    }

    //TODO

    /**
     * @param group
     */
    public void pushScheduleByGroup(Group group) {
        List<User> users = userService.findAll();
        for (User user : users) {
            if (user.getSettings().getSelectedGroup().equals(group.getGroupName())) {
                pushBot.executeMessage(new SendMessage()
                        .setChatId(user.getUserToken())
                        .setText("<b>Рассписание для вашей " + group.getGroupName() + " группы обновлено!\n Желаете просмотреть? Введите: \n /getschedule - на неделю, /now - на сегодня </b>")
                        .setParseMode("html"));
            }
        }
    }


}