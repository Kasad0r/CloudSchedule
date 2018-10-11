package org.ssunion.cloudschedule.telegram.admin.logic;

import org.ssunion.cloudschedule.telegram.admin.domain.Trigger;
import org.ssunion.cloudschedule.telegram.admin.domain.User;
import org.ssunion.cloudschedule.telegram.admin.repo.UserRepo;
import org.telegram.telegrambots.meta.api.objects.Update;

public final class Trigges {
    private UserRepo ur;
    private static Trigges trigges = new Trigges();

    private Trigges() {

    }

    public static Trigges getInstance() {
        return trigges;
    }

    public void check(Update update) {
        User user = ur.findByUserToken(update.getMessage().getChatId());
        if (user.getTrigger() != Trigger.NONE) {
            if (user.getTrigger() == Trigger.SET_GROUP_STAGE_1) {

            }
        }
    }

    private void groupStage1(Update update, User user) {
        String callbackData = update.getCallbackQuery().getData();

        if (callbackData.equals("course_1")) {

        } else if (callbackData.equals("course_2")) {

        } else if (callbackData.equals("course_3")) {

        } else if (callbackData.equals("course_4")) {

        }

    }
}
