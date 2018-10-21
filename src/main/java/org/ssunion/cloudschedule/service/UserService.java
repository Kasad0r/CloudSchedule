package org.ssunion.cloudschedule.service;

import org.ssunion.cloudschedule.domain.telegram.pushbot.User;

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
}
