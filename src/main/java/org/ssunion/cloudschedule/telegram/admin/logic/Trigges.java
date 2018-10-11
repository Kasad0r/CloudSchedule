package org.ssunion.cloudschedule.telegram.admin.logic;

import org.ssunion.cloudschedule.telegram.admin.AdminBot;
import org.ssunion.cloudschedule.telegram.admin.domain.Trigger;
import org.ssunion.cloudschedule.telegram.admin.domain.User;
import org.ssunion.cloudschedule.telegram.admin.menus.GroupMenu;
import org.ssunion.cloudschedule.telegram.admin.menus.StaticMessages;
import org.ssunion.cloudschedule.telegram.admin.repo.UserRepo;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public final class Trigges {
    private UserRepo ur;
    private AdminBot adminBot = AdminBot.getInstance();
    private static Trigges trigges = new Trigges();

    private Trigges() {

    }

    public static Trigges getInstance() {
        return trigges;
    }

    //TODO В классе StringUtils можно парсить строку в LocalTime
    public void check(Update update) {
        User user = ur.findByUserToken(update.getMessage().getChatId());
        if (user.getTrigger() != Trigger.NONE) {
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
                    adminBot.updateMessage(editMessageText);
                    user.setTrigger(Trigger.NONE);
                } else if (data.equals("nogroup")) {
                    AdminBot.getInstance().push(StaticMessages.createMessage("Сожалеем :(", update.getCallbackQuery().getMessage().getChatId()));
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
        adminBot.updateMessage(editMessageText);
    }
}
