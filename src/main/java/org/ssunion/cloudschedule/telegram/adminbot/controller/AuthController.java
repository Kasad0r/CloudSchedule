package org.ssunion.cloudschedule.telegram.adminbot.controller;

import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author kasad0r
 */
@Controller
public class AuthController {
    public boolean checkAdmin(Update update) {
        return false;
    }
}
