package org.ssunion.cloudschedule.telegram.pushbot.menus;

import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.ssunion.cloudschedule.telegram.pushbot.domain.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class UserStatusMenu {
    public static void execute(User user) {
        SendMessage sendMessage = new SendMessage().setChatId(user.getUserToken());
        sendMessage.setParseMode("html");
        sendMessage.setText("<b>Статус:</b><br>" +
                "<i>ID: </i>" + user.getUserToken() +
                "<i>UserName: </i>" + user.getUsername() +
                "<i>FullName: </i>" + user.getFirstname() + " " + user.getLastname() +
                "<i>Admin Notice: </i>" + (user.getSettings().isAdminNotice() ? "ON" : "OFF") +
                "<i>Selected group: </i>" + user.getSettings().getSelectedGroup() +
                "<i>Time To Send: </i>" + user.getSettings().getTimeToSendSchedule());
        PushBot.getInstance().push(sendMessage);
    }
}
