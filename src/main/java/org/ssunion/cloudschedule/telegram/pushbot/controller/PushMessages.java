package org.ssunion.cloudschedule.telegram.pushbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ssunion.cloudschedule.domain.telegram.pushbot.User;
import org.ssunion.cloudschedule.service.impl.UserServiceImpl;
import org.ssunion.cloudschedule.telegram.pushbot.PushBot;
import org.ssunion.cloudschedule.telegram.pushbot.messages.MessageFactory;

import java.util.List;

/**
 * @author kasad0r
 */
@Component
public class PushMessages {
    private final PushBot pushBot;
    private final UserServiceImpl userService;

    @Autowired
    public PushMessages(PushBot pushBot, UserServiceImpl userService) {
        this.pushBot = pushBot;
        this.userService = userService;
    }


    //TODO

    /**
     * @param groupName
     * @param message
     */
    public boolean forGroupImportant(String groupName, String message) {
        List<User> users = userService.getUserBySelectedGroup(groupName);
        if (users != null) {
            MessageFactory.create(users, message);
            return true;
        } else {
            return false;
        }
    }
//TODO

    /**
     * @param groupName
     * @param message
     */
    public void forGroupInconsiderable(String groupName, String message) {

    }
//TODO

    /**
     * @param groupName
     * @param message
     */
    public void forCourseImportant(String groupName, String message) {

    }
//TODO

    /**
     * @param groupName
     * @param message
     */
    public void forCourseInconsiderable(String groupName, String message) {

    }
//TODO

    /**
     * @param groupName
     * @param message
     */
    public void forAllImportant(String groupName, String message) {

    }
//TODO

    /**
     * @param groupName
     * @param message
     */
    public void forAllInconsiderable(String groupName, String message) {

    }
}
