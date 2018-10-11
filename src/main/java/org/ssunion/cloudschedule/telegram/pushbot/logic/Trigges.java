package org.ssunion.cloudschedule.telegram.pushbot.logic;

import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.ssunion.cloudschedule.telegram.pushbot.domain.Trigger;
import org.ssunion.cloudschedule.telegram.pushbot.domain.User;
import org.ssunion.cloudschedule.telegram.pushbot.menus.GroupMenu;
import org.ssunion.cloudschedule.telegram.pushbot.menus.StaticMessages;
import org.ssunion.cloudschedule.telegram.pushbot.repo.UserRepo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public final class Trigges {
    private UserRepo ur;
    private PushBot pushBot = PushBot.getInstance();
    private static Trigges trigges = new Trigges();

    private Trigges() {

    }

    public static Trigges getInstance() {
        return trigges;
    }

    //TODO В классе StringUtils можно парсить строку в LocalTime
    public void check(Update update) {
        User user = null;
        if (update.hasMessage()) {
            user = ur.findByUserToken(update.getMessage().getChatId());
        } else if (update.hasCallbackQuery()) {
            user = ur.findByUserToken(update.getCallbackQuery().getMessage().getChatId());
        }

        if (user != null && user.getTrigger() != Trigger.NONE) {
            if (user.getTrigger() == Trigger.SET_GROUP_STAGE_1) {
                groupStage1(update);
                user.setTrigger(Trigger.SET_GROUP_STAGE_2);
                ur.save(user);
            } else if (user.getTrigger() == Trigger.SET_GROUP_STAGE_2) {
                String data = update.getCallbackQuery().getData();
                if (data.contains("group_")) {
                    EditMessageText editMessageText = new EditMessageText();
                    user.getSettings().setSelectedGroup(data.substring(6));
                    editMessageText.setText("Вы выбрали " + user.getSettings().getSelectedGroup() + " группу");
                    editMessageText.setChatId(update.getCallbackQuery().getMessage().getChatId());
                    pushBot.updateMessage(editMessageText);
                    user.setTrigger(Trigger.NONE);
                } else if (data.equals("nogroup")) {
                    PushBot.getInstance().push(StaticMessages.createMessage("Сожалеем :(", update.getCallbackQuery().getMessage().getChatId()));
                }
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
                GroupMenu.getGroupListByCourse("1", chatId);
                break;
            case "course_2":
                editMessageText.setText("Вы выбрали 2 курс");
                editMessageText.setChatId(chatId);
                GroupMenu.getGroupListByCourse("2", chatId);
                break;
            case "course_3":
                editMessageText.setText("Вы выбрали 3 курс");
                editMessageText.setChatId(chatId);
                GroupMenu.getGroupListByCourse("3", chatId);
                break;
            case "course_4":
                editMessageText.setText("Вы выбрали 4 курс");
                editMessageText.setChatId(chatId);
                GroupMenu.getGroupListByCourse("4", chatId);
                break;
        }
        pushBot.updateMessage(editMessageText);
    }
}
