package org.ssunion.cloudschedule.telegram.admin.logic.controller;

import org.ssunion.cloudschedule.telegram.admin.domain.User;
import org.ssunion.cloudschedule.telegram.admin.repo.UserRepo;
import org.telegram.telegrambots.meta.api.objects.Update;

public final class UserController {
    private UserRepo userRepo;

    public User checkUser(Update u) {
        User user = userRepo.findByUserToken(u.getMessage().getChatId());
        if (user == null) {
            user = new User();
            user.setUsername(u.getMessage().getFrom().getUserName());
            user.setFirstname(u.getMessage().getFrom().getFirstName());
            user.setLastname(u.getMessage().getFrom().getLastName());
            user.setUserToken(u.getMessage().getChatId());
            user.getSettings().setAdminNotice(true);
            userRepo.save(user);
            return user;
        } else return user;
    }
}
