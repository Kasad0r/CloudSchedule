package org.ssunion.cloudschedule.telegram;

import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class BotsController {
    public void run() throws TelegramApiRequestException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        PushBot pushBot = PushBot.getInstance();
        telegramBotsApi.registerBot(pushBot);
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        BotsController botsController = new BotsController();
        try {
            botsController.run();
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
