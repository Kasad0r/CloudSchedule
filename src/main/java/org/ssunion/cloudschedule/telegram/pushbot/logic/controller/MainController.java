package org.ssunion.cloudschedule.telegram.pushbot.logic.controller;

import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.ssunion.cloudschedule.telegram.pushbot.logic.Commands;
import org.ssunion.cloudschedule.telegram.pushbot.logic.Trigges;
import org.telegram.telegrambots.meta.api.objects.Update;

public final class MainController {
    private static MainController mainController = new MainController();
    private PushBot pushBot = PushBot.getInstance();
    private UserController uc = new UserController();
    private Commands commands = Commands.getInstance();
    private Trigges trigges = Trigges.getInstance();

    private MainController() {

    }

    public static MainController getInstance() {
        return mainController;
    }

    public void executeUpdate(Update update) {
        if (update.hasMessage()) {
            System.out.println(update);
            uc.checkUser(update);
            trigges.check(update);
            if (update.getMessage().hasText()) {
                commands.checkCommand(update.getMessage());
            }
        } else if (update.hasCallbackQuery()) {
            trigges.check(update);
        }
    }
}
