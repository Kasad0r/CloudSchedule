package org.ssunion.cloudschedule.telegram.adminbot.controller;

import org.springframework.stereotype.Controller;
import org.ssunion.cloudschedule.service.impl.AdminServiceImpl;
import org.ssunion.cloudschedule.telegram.adminbot.domain.Admin;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author kasad0r
 */
@Controller
public class AuthController {
    private final AdminServiceImpl adminService;

    public AuthController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    public boolean checkAdmin(Update update) {
        System.out.println(update.getMessage().getChatId());
        Admin admin = adminService.getByToken(update.getMessage().getChatId());
        System.out.println(admin);
        System.out.println(update.getMessage().getFrom().getUserName() + " USERNAME");
        if (admin == null) {
            return false;
        } else return update.getMessage().getFrom().getUserName().equalsIgnoreCase(admin.getUsername());
    }

}
