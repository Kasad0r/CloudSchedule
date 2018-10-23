package org.ssunion.cloudschedule.telegram.adminbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.ssunion.cloudschedule.service.impl.AdminServiceImpl;
import org.ssunion.cloudschedule.telegram.adminbot.AdminBot;
import org.ssunion.cloudschedule.telegram.adminbot.domain.Admin;
import org.ssunion.cloudschedule.telegram.adminbot.domain.AdminTrigger;
import org.ssunion.cloudschedule.telegram.adminbot.menus.MainMenu;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author kasad0r
 */
@Controller
public class AdminBotController {
    private final
    AuthController authController;
    private AdminBot adminBot;
    private final AdminServiceImpl adminService;
    private final UpdateSchedule updateSchedule;

    @Autowired
    public AdminBotController(AuthController authController, AdminServiceImpl adminService, UpdateSchedule updateSchedule) {
        this.authController = authController;
        this.adminService = adminService;
        this.updateSchedule = updateSchedule;
    }

    @Autowired
    public void setAdminBot(AdminBot adminBot) {
        this.adminBot = adminBot;
    }

    public void performUpdate(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                if (authController.checkAdmin(update)) {
                    String textMessage = update.getMessage().getText();
                    long chatId = update.getMessage().getChatId();
                    Admin admin = adminService.getByToken(chatId);

                    if (textMessage.equalsIgnoreCase("/start")) {

                        adminBot.executeMessage(MainMenu.get(chatId));
                        admin.setAdminTrigger(AdminTrigger.MAINMENU);
                        adminService.updateAdmin(admin);
                    } else if (textMessage.equals("Оновити розклад") &&
                            admin.getAdminTrigger() == AdminTrigger.MAINMENU) {
                        updateSchedule.check(update);
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {

        }
    }


}
