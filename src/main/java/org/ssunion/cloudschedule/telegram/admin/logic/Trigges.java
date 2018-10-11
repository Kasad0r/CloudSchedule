package org.ssunion.cloudschedule.telegram.admin.logic;

import org.ssunion.cloudschedule.telegram.admin.AdminBot;
import org.ssunion.cloudschedule.telegram.admin.domain.Trigger;
import org.ssunion.cloudschedule.telegram.admin.domain.User;
import org.ssunion.cloudschedule.telegram.admin.menus.GroupMenu;
import org.ssunion.cloudschedule.telegram.admin.menus.StaticMessages;
import org.ssunion.cloudschedule.telegram.admin.repo.UserRepo;
import org.telegram.telegrambots.meta.api.objects.Update;

public final class Trigges {
    private UserRepo ur;
    private static Trigges trigges = new Trigges();

    public Trigges() {

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
                    user.getSettings().setSelectedGroup(data.substring(6));

                } else if (data.equals("nogroup")) {
                    AdminBot.getInstance().push(StaticMessages.createMessage("Сожалеем :(", update.getCallbackQuery().getMessage().getChatId()));
                }
            }
        }
    }


    private void groupStage1(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        switch (callbackData) {
            case "course_1":
                GroupMenu.getGroupListByCourse("1", chatId);
                break;
            case "course_2":
                GroupMenu.getGroupListByCourse("2", chatId);
                break;
            case "course_3":
                GroupMenu.getGroupListByCourse("3", chatId);
                break;
            case "course_4":
                GroupMenu.getGroupListByCourse("4", chatId);
                break;
        }
    }
}
