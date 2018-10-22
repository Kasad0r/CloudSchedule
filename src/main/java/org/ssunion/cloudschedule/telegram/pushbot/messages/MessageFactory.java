package org.ssunion.cloudschedule.telegram.pushbot.messages;

import org.ssunion.cloudschedule.domain.telegram.pushbot.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kasad0r
 */
public class MessageFactory {
    public static SendMessage createBold(long chatId, String message) {
        return new SendMessage().setChatId(chatId).setText(String.format("<b>%s</b>", message)).setParseMode("html");
    }

    public static List<SendMessage> createBold(List<User> users, String message) {
        return users.stream().map(u -> createBold(u.getUserToken(), message)).collect(Collectors.toList());
    }

    public static List<SendMessage> createBold(long chatId, List<String> messages) {
        return messages.stream().map(m -> create(chatId, m)).collect(Collectors.toList());
    }

    public static SendMessage create(long chatId, String message) {
        return new SendMessage().setChatId(chatId).setText(message).setParseMode("html");
    }
}
