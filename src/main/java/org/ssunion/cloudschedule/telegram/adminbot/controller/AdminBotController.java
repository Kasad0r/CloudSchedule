package org.ssunion.cloudschedule.telegram.adminbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.ssunion.cloudschedule.telegram.adminbot.AdminBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author kasad0r
 */
@Controller
public class AdminBotController {
    private String commandStart = "/start";
    private AdminBot adminBot;
    private AuthController authController;

    public void performUpdate(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                Long chatId = update.getMessage().getChatId();
                if (messageText.equals("/start")) {
                    authController.registerNonActivatedAdmin(update);
                }
                if (authController.checkNonActivatedAdmin(update)) {
                    if (messageText.equals("Активувати")) {

                    }
                }

            }
        } else if (update.hasCallbackQuery()) {

        }
    }

    @Autowired
    public void setAdminBot(AdminBot adminBot) {
        this.adminBot = adminBot;
    }

    @Autowired
    public void setAuthController(AuthController authController) {
        this.authController = authController;
    }
}
