package org.ssunion.cloudschedule.telegram.pushbot.menus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.domain.telegram.pushbot.User;
import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class UserStatusMenu {

    private final PushBot pushBot;

    @Autowired
    public UserStatusMenu(PushBot pushBot) {
        this.pushBot = pushBot;
    }

    public void execute(User user) {
        SendMessage sendMessage = new SendMessage().setChatId(user.getUserToken());
        sendMessage.setParseMode("html");
        sendMessage.setText("<b>Статус:</b>\n" +
                "<i>ID: </i>" + user.getUserToken() +
                "\n<i>UserName: </i>" + user.getUsername() +
                "\n<i>FullName: </i>" + user.getFirstname() + " " + user.getLastname() +
                "\n<i>Admin Notice: </i>" + (user.getSettings().isAdminNotice() ? "ON" : "OFF") +
                "\n<i>Selected group: </i>" + (user.getSettings().getSelectedGroup() == null ? "Не выбран" : user.getSettings().getSelectedGroup()) +
                "\n<i>Time To Send: </i>" + (user.getSettings().getTimeToSendSchedule() == null ? "Не выбран" : user.getSettings().getTimeToSendSchedule()));
        pushBot.executeMessage(sendMessage);
    }
}
