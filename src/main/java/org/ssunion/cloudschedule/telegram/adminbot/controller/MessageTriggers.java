package org.ssunion.cloudschedule.telegram.adminbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.service.impl.AdminServiceImpl;
import org.ssunion.cloudschedule.telegram.adminbot.domain.Admin;
import org.ssunion.cloudschedule.telegram.adminbot.domain.AdminTrigger;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author kasad0r
 */
@Component
public class MessageTriggers {
    private AdminServiceImpl adminService;
    private AuthController authController;

    public MessageTriggers(AuthController authController) {
        this.authController = authController;
    }

    public void check(Update update) {
        Admin admin = adminService.getByToken(update.getMessage().getChatId());

        if (admin.getAdminTrigger() == AdminTrigger.ACTIVATE) {

            authController.registerAdminByActivationCode(update);

        }
    }

    public void updateTrigger(Update update, AdminTrigger adminTrigger) {
        Admin admin = adminService.getByToken(update.getMessage().getChatId());
        admin.setAdminTrigger(adminTrigger);
        adminService.updateAdmin(admin);
    }

    @Autowired
    public void setAdminService(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }
}
