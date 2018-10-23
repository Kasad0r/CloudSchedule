package org.ssunion.cloudschedule.service;

import org.ssunion.cloudschedule.telegram.pushbot.domain.User;

import java.util.List;

/**
 * @author kasad0r
 */
public interface UserService {
    User addUser(User user);

    void deleteUser(User user);

    List<User> findAll();

    boolean checkUser(Long userToken);

    User getUserByToken(Long userToken);

    List<User> getUsersBySelectedGroupAndNotice(String group, boolean notice);

    List<User> getUsersBySelectedGroup(String group);

    List<User> getUsersByCourse(String course);

    List<User> getUsersByCourseAndNotice(String course, boolean notice);

    List<User> getUsersByAdminNotice(boolean notice);

}
