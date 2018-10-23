package org.ssunion.cloudschedule.telegram.pushbot.menus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.ssunion.cloudschedule.telegram.pushbot.domain.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * @author kasad0r
 */
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
                "\n<i>Имя пользователя: </i>" + (user.getFirstname() == null ? "" : user.getFirstname()) + " " + (user.getLastname() == null ? "" : user.getLastname()) +
                "\n<i>Сообщение от администрации: </i>" + (user.getSettings().isAdminNotice() ? "ВКЛ" : "ВЫКЛ") +
                "\n<i>Выбранная группа: </i>" + (user.getSettings().getSelectedGroup() == null ? "Не выбран" : user.getSettings().getSelectedGroup()) +
                "\n<i>Время для отправки: </i>" + (user.getSettings().getTimeToSendSchedule() == null ? "Не выбран" : user.getSettings().getTimeToSendSchedule()));
        pushBot.executeMessage(sendMessage);
    }
}
