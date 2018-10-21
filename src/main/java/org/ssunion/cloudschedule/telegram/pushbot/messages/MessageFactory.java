package org.ssunion.cloudschedule.telegram.pushbot.messages;

import org.ssunion.cloudschedule.domain.telegram.pushbot.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kasad0r
 */
public class MessageFactory {
    public static SendMessage create(long chatId, String message) {
        return new SendMessage().setChatId(chatId).setText(String.format("<b>%s</b>", message)).setParseMode("html");
    }

    public static List<SendMessage> create(List<User> users, String message) {
        return users.stream().map(u -> create(u.getUserToken(), message)).collect(Collectors.toList());
    }
}
