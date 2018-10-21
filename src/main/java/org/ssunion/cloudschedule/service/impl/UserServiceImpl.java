package org.ssunion.cloudschedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssunion.cloudschedule.domain.telegram.pushbot.User;
import org.ssunion.cloudschedule.repo.UserRepository;
import org.ssunion.cloudschedule.service.UserService;

import java.util.List;

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
}
