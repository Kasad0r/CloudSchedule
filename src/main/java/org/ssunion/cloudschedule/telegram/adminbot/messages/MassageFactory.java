package org.ssunion.cloudschedule.telegram.adminbot.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.telegram.adminbot.AdminBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * @author kasad0r
 */
@Component
public class MassageFactory {

    private static AdminBot adminBot;

    public static void create(long chatId, String message) {
        adminBot.executeMessage(new SendMessage().setChatId(chatId).setText(message));
    }

    @Autowired
    public void setAdminBot(AdminBot adminBot) {
        MassageFactory.adminBot = adminBot;
    }
}
