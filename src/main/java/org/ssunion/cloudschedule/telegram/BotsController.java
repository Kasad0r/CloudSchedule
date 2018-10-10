package org.ssunion.cloudschedule.telegram;

import org.ssunion.cloudschedule.telegram.admin.AdminBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class BotsController {
    public void run() throws TelegramApiRequestException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(AdminBot.getInstance());
    }
}
