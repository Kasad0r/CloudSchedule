package org.ssunion.cloudschedule.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.ssunion.cloudschedule.domain.base.Day;
import org.ssunion.cloudschedule.domain.base.Group;
import org.ssunion.cloudschedule.service.impl.GroupServiceImpl;
import org.ssunion.cloudschedule.telegram.pushbot.controller.PushBotController;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author kasad0r
 */
@Controller
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ScheduleController {
    private GroupServiceImpl groupService;
    private PushBotController pushBotController;

    @Autowired
    public void setGroupService(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @Autowired
    public void setPushBotController(PushBotController pushBotController) {
        this.pushBotController = pushBotController;
    }

    public void updateSchedule(List<Group> updatesList) {
        for (Group group : updatesList) {
            updateSchedule(group);
        }
    }

    public void updateSchedule(Group group) {
        Group tempBDGroup = groupService.getByName(group.getGroupName());
        if (tempBDGroup == null) {
            groupService.addGroup(group);
        } else {
            AtomicBoolean update = new AtomicBoolean(false);
            System.out.println(tempBDGroup.getWeekSchedule());
            System.out.println(group.getWeekSchedule());
            List<Day> weekToUpdate = group.getWeekSchedule();
            group.getWeekSchedule().forEach(gr -> {
                if (tempBDGroup.getWeekSchedule().stream().anyMatch(temp -> temp.equals(gr))) {
                    update.set(true);
                }
            });

            /*if () {
                tempBDGroup.getWeekSchedule().set(i, weekToUpdate.get(i));
                tempBDGroup.setLastUpdate(LocalDate.now().toString());
                update = true;
            }*/
            if (update.get()) {
                tempBDGroup.setWeekSchedule(tempBDGroup.getWeekSchedule());
                tempBDGroup.setLastUpdate(LocalDate.now().toString());
                pushBotController.pushScheduleByGroup(tempBDGroup);
            }
            groupService.editGroup(tempBDGroup);
        }
    }


}
