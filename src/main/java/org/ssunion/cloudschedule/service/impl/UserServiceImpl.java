package org.ssunion.cloudschedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssunion.cloudschedule.repo.UserRepository;
import org.ssunion.cloudschedule.service.UserService;
import org.ssunion.cloudschedule.telegram.pushbot.domain.User;

import java.util.List;

/**
 * @author kasad0r
 */
@Service
/*@Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)*/
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean checkUser(Long userToken) {
        return userRepository.findFirstByUserToken(userToken) != null;
    }

    @Override
    public User getUserByToken(Long userToken) {
        return userRepository.findFirstByUserToken(userToken);
    }

    @Override
    public List<User> getUsersBySelectedGroupAndNotice(String group, boolean notice) {
        return userRepository.findUsersBySettings_SelectedGroupAndSettings_AdminNotice(group, notice);
    }

    @Override
    public List<User> getUsersBySelectedGroup(String group) {
        return userRepository.findUsersBySettings_SelectedGroup(group);
    }

    @Override
    public List<User> getUsersByCourse(String course) {
        return userRepository.findUsersBySettings_SelectedGroupStartingWith(course);
    }

    @Override
    public List<User> getUsersByCourseAndNotice(String course, boolean notice) {
        return userRepository.findUsersBySettings_SelectedGroupStartingWithAndSettings_AdminNotice(course, notice);
    }

    @Override
    public List<User> getUsersByAdminNotice(boolean notice) {
        return userRepository.findUsersBySettings_AdminNotice(notice);
    }

}
