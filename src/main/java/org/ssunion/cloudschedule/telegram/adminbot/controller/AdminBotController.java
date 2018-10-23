package org.ssunion.cloudschedule.telegram.adminbot.controller;

import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author kasad0r
 */
@Controller
public class AdminBotController {
    public void performUpdate(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {

            }
        } else if (update.hasCallbackQuery()) {

        }
    }


}
