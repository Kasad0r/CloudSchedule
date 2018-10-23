package org.ssunion.cloudschedule.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.telegram.pushbot.domain.User;

import java.util.List;

/**
 * @author kasad0r
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUserToken(long userToken);

    List<User> findUsersBySettings_SelectedGroupAndSettings_AdminNotice(String selectedGroup, boolean adminNotice);

    List<User> findUsersBySettings_SelectedGroup(String selectedGroup);

    List<User> findUsersBySettings_SelectedGroupStartingWith(String course);

    List<User> findUsersBySettings_SelectedGroupStartingWithAndSettings_AdminNotice(String course, boolean adminNotice);

    //TODO NOT CHEKED!
    List<User> findUsersBySettings_AdminNotice(boolean adminNotice);
}
