package org.ssunion.cloudschedule.telegram.pushbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.domain.telegram.pushbot.Trigger;
import org.ssunion.cloudschedule.domain.telegram.pushbot.User;
import org.ssunion.cloudschedule.service.impl.UserServiceImpl;
import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.ssunion.cloudschedule.telegram.pushbot.menus.GroupMenu;
import org.ssunion.cloudschedule.telegram.pushbot.messages.MessageFactory;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author kasad0r
 */
@Component
public final class Trigges {
    private final UserServiceImpl userService;
    private final PushBot pushBot;
    private final GroupMenu groupMenu;

    @Autowired
    private Trigges(UserServiceImpl userService, PushBot pushBot, GroupMenu groupMenu) {
        this.userService = userService;
        this.pushBot = pushBot;
        this.groupMenu = groupMenu;
    }

    //TODO В классе StringUtils можно парсить строку в LocalTime
    public void check(Update update) {
        User user = null;
        if (update.hasMessage()) {
            user = userService.getUserByToken(update.getMessage().getChatId());
        } else if (update.hasCallbackQuery()) {
            user = userService.getUserByToken(update.getCallbackQuery().getMessage().getChatId());
        }

        if (user != null && user.getTrigger() != Trigger.NONE) {
            if (user.getTrigger() == Trigger.SET_GROUP_STAGE_1) {
                groupStage1(update);
                user.setTrigger(Trigger.SET_GROUP_STAGE_2);
                userService.addUser(user);
            } else if (user.getTrigger() == Trigger.SET_GROUP_STAGE_2) {
                String data = update.getCallbackQuery().getData();
                long chatId = update.getCallbackQuery().getMessage().getChatId();
                if (data.contains("group_")) {
                    EditMessageText editMessageText = new EditMessageText();
                    user.getSettings().setSelectedGroup(data.substring(6));
                    editMessageText.setText("Вы выбрали " + user.getSettings().getSelectedGroup() + " группу");
                    editMessageText.setChatId(chatId);
                    editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    pushBot.updateMessage(editMessageText);
                    user.setTrigger(Trigger.NONE);
                    userService.addUser(user);
                } else if (data.equals("nogroup")) {
                    pushBot.executeMessage(MessageFactory.createBold(chatId, "Сожалеем :("));
                    pushBot.updateMessage(
                            new EditMessageText()
                                    .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                                    .setText("Ожидайте обновления рассписания.")
                                    .setChatId(chatId));
                    user.setTrigger(Trigger.NONE);
                    userService.addUser(user);
                }
            } else if (user.getTrigger() == Trigger.SET_TIME_TO_PUSH) {
                user.getSettings().setTimeToSendSchedule(update.getCallbackQuery().getData().substring(5) + ":00");
                pushBot.updateMessage(
                        new EditMessageText()
                                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                                .setMessageId(update.getCallbackQuery().getMessage().getMessageId())
                                .setText("Вы выбрали:" + user.getSettings().getTimeToSendSchedule()));
                user.setTrigger(Trigger.NONE);
                userService.addUser(user);
            }
        }
    }

    private void groupStage1(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        EditMessageText editMessageText = new EditMessageText();
        switch (callbackData) {
            case "course_1":
                editMessageText.setText("Вы выбрали 1 курс");
                editMessageText.setChatId(chatId);
                editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                groupMenu.getGroupListByCourse("1", chatId);
                break;
            case "course_2":
                editMessageText.setText("Вы выбрали 2 курс");
                editMessageText.setChatId(chatId);
                editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                groupMenu.getGroupListByCourse("2", chatId);
                break;
            case "course_3":
                editMessageText.setText("Вы выбрали 3 курс");
                editMessageText.setChatId(chatId);
                editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                groupMenu.getGroupListByCourse("3", chatId);
                break;
            case "course_4":
                editMessageText.setText("Вы выбрали 4 курс");
                editMessageText.setChatId(chatId);
                editMessageText.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                groupMenu.getGroupListByCourse("4", chatId);
                break;
            //TODO Если нужно, сейчас ничего придумать не могу
            default:
                break;
        }
        editMessageText.setText("<b>" + editMessageText.getText() + "</b>");
        editMessageText.setParseMode("html");
        pushBot.updateMessage(editMessageText);
    }
}
