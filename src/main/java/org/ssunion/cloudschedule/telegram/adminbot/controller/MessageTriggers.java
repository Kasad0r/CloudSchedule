package org.ssunion.cloudschedule.telegram.adminbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.service.impl.ActivationCodeServiceImpl;
import org.ssunion.cloudschedule.service.impl.AdminServiceImpl;
import org.ssunion.cloudschedule.telegram.adminbot.domain.ActivationCode;
import org.ssunion.cloudschedule.telegram.adminbot.domain.Admin;
import org.ssunion.cloudschedule.telegram.adminbot.domain.AdminMessage;
import org.ssunion.cloudschedule.telegram.adminbot.domain.AdminTrigger;
import org.ssunion.cloudschedule.telegram.adminbot.menus.InfoMenu;
import org.ssunion.cloudschedule.telegram.adminbot.messages.MessageFactory;
import org.ssunion.cloudschedule.telegram.adminbot.messages.SendAdminMessageFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author kasad0r
 */
@Component
public class MessageTriggers {
    private AdminServiceImpl adminService;
    private AuthController authController;
    private ActivationCodeServiceImpl activationCodeService;

    public MessageTriggers(AuthController authController, ActivationCodeServiceImpl activationCodeService) {
        this.authController = authController;
        this.activationCodeService = activationCodeService;
    }

    public void check(Update update) {
        Admin admin = adminService.getByToken(update.getMessage().getChatId());
        String messageText = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        if (admin.getAdminTrigger() == AdminTrigger.ACTIVATE) {
            authController.registerAdminByActivationCode(update);
        } else if (admin.getAdminTrigger() == AdminTrigger.MAINMENU) {
            if (messageText.equals("Сгенерувати код")) {
                ActivationCode activationCode = new ActivationCode();
                activationCode.setGeneratedBy(admin.getUserToken());
                activationCode.setActivated(false);
                activationCode.setUuid(UUID.randomUUID().toString());
                MessageFactory.createBold(chatId, "Ваш код:\n(Він є одноразовим)");
                MessageFactory.createBold(chatId, activationCode.getUuid());
                activationCodeService.addActivationCode(activationCode);
            } else if (messageText.equals("Обновить рассписание")) {
                updateTrigger(update, AdminTrigger.UpadateScheduleSTAGE1);
            } else if (messageText.equals("Створити повідомлення")) {
                updateTrigger(update, AdminTrigger.SendMessageSTAGE1);
                MessageFactory.createBold(chatId, "Відправте текст, який хочете надіслати:");

            } else if (messageText.equals("Інфо")) {
                MessageFactory.createBold(chatId, InfoMenu.get(chatId).getText());
            }

        } else if (admin.getAdminTrigger() == AdminTrigger.SendMessageSTAGE1) {
            AdminMessage adminMessage = new AdminMessage();
            adminMessage
                    .setTime(LocalDateTime.now().toString())
                    .setText(update.getMessage().getText());
            admin.getAdminMessage().add(adminMessage);
            SendAdminMessageFactory.getTypeOfAdminMessage(chatId);
            updateTrigger(update, AdminTrigger.UpdateScheduleSTAGE2);
            admin.setCurrentMessage(adminMessage.getId());
            adminService.updateAdmin(admin);
            System.out.println(adminMessage);
        } else if (admin.getAdminTrigger() == AdminTrigger.SendMessageSTAGE2) {

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
