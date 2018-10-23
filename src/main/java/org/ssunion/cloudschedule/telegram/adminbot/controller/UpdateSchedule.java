package org.ssunion.cloudschedule.telegram.adminbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.service.impl.AdminServiceImpl;
import org.ssunion.cloudschedule.telegram.adminbot.AdminBot;
import org.ssunion.cloudschedule.telegram.adminbot.domain.Admin;
import org.ssunion.cloudschedule.telegram.adminbot.domain.AdminTrigger;
import org.ssunion.cloudschedule.telegram.pushbot.messages.MessageFactory;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author kasad0r
 */
@Component
public class UpdateSchedule {
    private final
    AdminServiceImpl adminService;

    private AdminBot adminBot;


    public void check(Update update) {
        Admin admin = adminService.getByToken(update.getMessage().getChatId());
        if (admin.getAdminTrigger() == AdminTrigger.MAINMENU) {
            stage1(admin.getUserToken());
            admin.setAdminTrigger(AdminTrigger.UpadateScheduleSTAGE1);
        }
    }

    private void stage1(long chatId) {
        adminBot.executeMessage(MessageFactory.create(chatId, "<b>Оновлення розкладу:\n Завантажте файл розкладу...\n" +
                "УВАГА!!! Використовувати тільки файл даний розробниками, не міняючи структуру та назву документа!!!</b>"));
        adminBot.executeDocument(MessageFactory.createDocument(chatId));
    }

    @Autowired
    public void setAdminBot(AdminBot adminBot) {
        this.adminBot = adminBot;
    }

    @Autowired
    public UpdateSchedule(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

}
