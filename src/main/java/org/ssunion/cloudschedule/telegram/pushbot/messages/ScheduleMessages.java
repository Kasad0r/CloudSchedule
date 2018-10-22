package org.ssunion.cloudschedule.telegram.pushbot.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.domain.base.Day;
import org.ssunion.cloudschedule.service.impl.GroupServiceImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * @author kasad0r
 */
@Component
public class ScheduleMessages {

    private final GroupServiceImpl groupService;


    @Autowired
    public ScheduleMessages(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }


    public SendMessage getNow(long chatId, String groupName) {
        List<Day> days = groupService.getByName(groupName).getWeekSchedule();
        DayOfWeek dayIndex = LocalDate.now().getDayOfWeek();
        System.out.println(dayIndex);
        if (dayIndex == DayOfWeek.MONDAY) {
            for (Day d : days
            ) {
                System.out.println(d);
                if (d.getDayName().equals("Пн")) {
                    System.out.println("+++++++++++++++++++");
                    return MessageFactory.create(chatId, d.getScheduleForTelegram());
                }
            }
        } else if (dayIndex == DayOfWeek.TUESDAY) {
            for (Day d : days
            ) {
                if (d.getDayName().equals("Вт")) {
                    return MessageFactory.create(chatId, d.getScheduleForTelegram());
                }
            }
        } else if (dayIndex == DayOfWeek.WEDNESDAY) {
            for (Day d : days
            ) {
                if (d.getDayName().equals("Ср")) {
                    return MessageFactory.create(chatId, d.getScheduleForTelegram());
                }
            }
        } else if (dayIndex == DayOfWeek.THURSDAY) {
            for (Day d : days
            ) {
                if (d.getDayName().equals("Чт")) {
                    return MessageFactory.create(chatId, d.getScheduleForTelegram());
                }
            }
        } else if (dayIndex == DayOfWeek.FRIDAY) {
            for (Day d : days
            ) {
                if (d.getDayName().equals("Пт")) {
                    return MessageFactory.create(chatId, d.getScheduleForTelegram());
                }
            }
        }
        return MessageFactory.create(chatId, "Что-то пошло не так");
    }
}
