package org.ssunion.cloudschedule.service;

import org.ssunion.cloudschedule.domain.telegram.pushbot.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    void deleteUser(User user);

    List<User> findAll();

    boolean checkUser(Long userToken);
}
