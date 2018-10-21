package org.ssunion.cloudschedule.telegram.pushbot.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * @author kasad0r
 */
public class MessageFactory {
    public static SendMessage create(long chatId, String message) {
        return new SendMessage().setChatId(chatId).setText(String.format("<b>%s</b>", message)).setParseMode("html");
    }
}
