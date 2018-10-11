package org.ssunion.cloudschedule.telegram.admin.logic.controller;

import org.ssunion.cloudschedule.telegram.admin.AdminBot;
import org.ssunion.cloudschedule.telegram.admin.logic.Commands;
import org.ssunion.cloudschedule.telegram.admin.logic.Trigges;
import org.telegram.telegrambots.meta.api.objects.Update;

public final class MainController {
    private static MainController mainController = new MainController();
    private AdminBot adminBot = AdminBot.getInstance();
    private UserController uc;
    private Commands commands= Commands.getInstance();
    private Trigges trigges = Trigges.getInstance();

    private MainController() {

    }

    public static MainController getInstance() {
        return mainController;
    }

    public void executeUpdate(Update update) {
        if (update.hasMessage()) {
            uc.checkUser(update);
            if (update.getMessage().hasText()) {
                /*String messageText = update.getMessage().getText();*/

                commands.checkCommand(update.getMessage());
            }
        } else if (update.hasCallbackQuery()) {

        }
    }
}
