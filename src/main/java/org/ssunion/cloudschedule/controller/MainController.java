package org.ssunion.cloudschedule.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.ssunion.cloudschedule.domain.base.Group;
import org.ssunion.cloudschedule.domain.base.GroupWrapper;
import org.ssunion.cloudschedule.scheduler.ScheduleController;
import org.ssunion.cloudschedule.service.impl.GroupServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kasad0r
 */
@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainController {
    private final
    GroupServiceImpl groupService;
    private final
    ScheduleController scheduleController;

    @Autowired
    public MainController(GroupServiceImpl groupService, ScheduleController scheduleController) {
        this.groupService = groupService;
        this.scheduleController = scheduleController;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {

       /* Group group = new Group();
        group.setGroupName("152");
        group.setLastUpdate(LocalDate.now().toString());
        Day day = new Day();
        day.setDayName("Пн");
        Lesson lesson = new Lesson();
        Flasher down = new Flasher();
        down.setName("Астрономия");
        down.setTeacher("Настя");
        Flasher upper = new Flasher();
        upper.setTeacher("Игорь");
        upper.setName("Попкультура");
        lesson.setDownWeek(down);
        lesson.setUpperWeek(upper);
        lesson.setStartTime("13:15");
        Lesson lesson2 = new Lesson();
        lesson2.setDownWeek(down);
        lesson2.setUpperWeek(upper);
        lesson2.setStartTime("14:40");
        Lesson lesson3 = new Lesson();
        lesson3.setDownWeek(down);
        lesson3.setUpperWeek(upper);
        lesson3.setStartTime("15:41");
        Lesson lesson4 = new Lesson();
        lesson4.setDownWeek(down);
        lesson4.setUpperWeek(upper);
        lesson4.setStartTime("13:15");
        Lesson lesson5 = new Lesson();
        lesson5.setDownWeek(down);
        lesson5.setUpperWeek(upper);
        lesson5.setStartTime("14:40");
        Lesson lesson6 = new Lesson();
        lesson6.setDownWeek(down);
        lesson6.setUpperWeek(upper);
        lesson6.setStartTime("15:41");
        Day day2 = new Day();
        day2.setDayName("Вт");
        day2.setLessons(Arrays.asList(lesson4, lesson5, lesson6));
        day.setLessons(Arrays.asList(lesson, lesson2, lesson3));
        Day day3 = new Day();
        Lesson lesson7 = new Lesson();
        lesson7.setDownWeek(down);
        lesson7.setUpperWeek(upper);
        lesson7.setStartTime("15:41");
        Lesson lesson8 = new Lesson();
        lesson8.setDownWeek(down);
        lesson8.setUpperWeek(upper);
        lesson8.setStartTime("13:15");
        Lesson lesson9 = new Lesson();
        lesson9.setDownWeek(down);
        lesson9.setUpperWeek(upper);
        lesson9.setStartTime("14:40");
        Lesson lesson10 = new Lesson();
        lesson10.setDownWeek(down);
        lesson10.setUpperWeek(upper);
        lesson10.setStartTime("15:41");
        day3.setDayName("Пт");
        Day day4 = new Day();
        day4.setDayName("Ср");
        Lesson lesson11 = new Lesson();
        lesson11.setDownWeek(down);
        lesson11.setUpperWeek(upper);
        lesson11.setStartTime("15:41");
        Lesson lesson12 = new Lesson();
        lesson12.setDownWeek(down);
        lesson12.setUpperWeek(upper);
        lesson12.setStartTime("13:15");
        Lesson lesson13 = new Lesson();
        lesson13.setDownWeek(down);
        lesson13.setUpperWeek(upper);
        lesson13.setStartTime("14:40");
        Lesson lesson14 = new Lesson();
        lesson14.setDownWeek(down);
        lesson14.setUpperWeek(upper);
        lesson14.setStartTime("15:41");
        Lesson lesson15 = new Lesson();
        lesson15.setDownWeek(down);
        lesson15.setUpperWeek(upper);
        lesson15.setStartTime("15:41");
        Lesson lesson16 = new Lesson();
        lesson16.setDownWeek(down);
        lesson16.setUpperWeek(upper);
        lesson16.setStartTime("13:15");
        Lesson lesson17 = new Lesson();
        lesson17.setDownWeek(down);
        lesson17.setUpperWeek(upper);
        lesson17.setStartTime("14:40");
        Lesson lesson18 = new Lesson();
        lesson18.setDownWeek(down);
        lesson18.setUpperWeek(upper);
        lesson18.setStartTime("15:41");
        Day day5 = new Day();
        day5.setDayName("Чт");
        day5.setLessons(Arrays.asList(lesson15, lesson16, lesson17, lesson18));
        day4.setLessons(Arrays.asList(lesson11, lesson12, lesson13, lesson14));
        day3.setLessons(Arrays.asList(lesson7, lesson8, lesson9, lesson10));
        group.setWeekSchedule(Arrays.asList(day, day2, day3, day4, day5));
        System.out.println(group);
        groupService.addGroup(group);
         scheduleController.updateSchedule(group);*/
        Iterable<Group> groups = groupService.getAll();
        List<GroupWrapper> groupWrapperList = new ArrayList<>();
        GroupWrapper groupWrapper = new GroupWrapper();
        groupWrapper.setGroupList(groups);
        groupWrapper.setCourseName("3 курс");
        groupWrapperList.add(groupWrapper);
        groupWrapperList.add(groupWrapper);
        model.put("courses", groupWrapperList);
        return "greeting";
    }
}
