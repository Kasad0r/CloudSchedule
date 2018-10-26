package org.ssunion.cloudschedule.telegram.adminbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.ssunion.cloudschedule.service.impl.ActivationCodeServiceImpl;
import org.ssunion.cloudschedule.service.impl.AdminServiceImpl;
import org.ssunion.cloudschedule.telegram.adminbot.AdminBot;
import org.ssunion.cloudschedule.telegram.adminbot.domain.ActivationCode;
import org.ssunion.cloudschedule.telegram.adminbot.domain.Admin;
import org.ssunion.cloudschedule.telegram.adminbot.domain.AdminTrigger;
import org.ssunion.cloudschedule.telegram.adminbot.menus.MainMenu;
import org.ssunion.cloudschedule.telegram.adminbot.menus.StartupMenu;
import org.ssunion.cloudschedule.telegram.adminbot.messages.MessageFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author kasad0r
 */
@Controller
public class AuthController {
    private AdminServiceImpl adminService;

    private ActivationCodeServiceImpl activationCodeService;
    private AdminBot adminBot;

    public boolean checkAdminRegistration(Update update) {
        Admin admin = adminService.getByToken(update.getMessage().getChatId());
        return admin != null && admin.isActivated();
    }

    public void registerNonActivatedAdmin(Update update) {
        if (adminService.getByToken(update.getMessage().getChatId()) == null) {
            Admin admin = new Admin();
            admin.setActivated(false);
            admin.setFirstname(update.getMessage().getFrom().getFirstName());
            admin.setLastname(update.getMessage().getFrom().getLastName());
            admin.setUserToken(update.getMessage().getChatId());
            admin.setUsername(update.getMessage().getFrom().getUserName());
            adminService.addAdmin(admin);
            adminBot.executeMessage(StartupMenu.get(update.getMessage().getChatId()));
        }
    }

    public boolean checkNonActivatedAdmin(Update update) {
        Admin admin = adminService.getByToken(update.getMessage().getChatId());
        return admin != null && !admin.isActivated();
    }

    public void registerAdminByActivationCode(Update update) {
        Admin admin = adminService.getByToken(update.getMessage().getChatId());
        ActivationCode code = activationCodeService.getByUUID(update.getMessage().getText());
        if (admin.isActivated()) {
            MessageFactory.create(update.getMessage().getChatId(), "Ваш аккаунт вже активований");
        } else if (!admin.isActivated()) {
            if (code != null) {
                if (code.isActivated()) {
                    MessageFactory.create(update.getMessage().getChatId(), "Цей код вже був використаний.");
                } else if (!code.isActivated()) {
                    admin.setActivated(true);
                    admin.setAdminTrigger(AdminTrigger.MAINMENU);
                    code.setActivated(true);
                    code.setActivatedBy(admin.getUserToken());
                    adminService.updateAdmin(admin);
                    activationCodeService.editActivationCode(code);
                    MessageFactory.createBold(update.getMessage().getChatId(), "Вітаємо з активацією акаунта!");
                    adminBot.executeMessage(MainMenu.get(update.getMessage().getChatId()));
                }
            } else if (!update.getMessage().getText().equals("Активувати")) {
                MessageFactory.createBold(update.getMessage().getChatId(), "Ви ввели недійсний код");
            }

        }

    }

    @Autowired
    public void setAdminService(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }


    @Autowired
    public void setActivationCode(ActivationCodeServiceImpl code) {
        this.activationCodeService = code;
    }

    @Autowired
    public void setAdminBot(AdminBot adminBot) {
        this.adminBot = adminBot;
    }
}
