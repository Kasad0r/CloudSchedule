package org.ssunion.cloudschedule.telegram.admin.controller;

import org.ssunion.cloudschedule.telegram.admin.AdminBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MainController {
    private static MainController mainController = new MainController();
    private AdminBot adminBot = AdminBot.getInstance();
    private UserController uc;

    private MainController() {

    }

    public static MainController getInstance() {
        return mainController;
    }

    public void executeUpdate(Update update) {
        if (update.hasMessage()) {
            uc.checkUser(update);
            if (update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();

            }
        } else if (update.hasCallbackQuery()) {

        }
    }
}
