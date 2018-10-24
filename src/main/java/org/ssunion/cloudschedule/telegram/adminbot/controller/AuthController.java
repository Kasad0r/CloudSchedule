package org.ssunion.cloudschedule.telegram.adminbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.ssunion.cloudschedule.service.impl.AdminServiceImpl;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AuthController {
    private AdminServiceImpl adminService;

    @Autowired
    public void setAdminService(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    public void checkAdminRegistration(Update update) {

    }
}
