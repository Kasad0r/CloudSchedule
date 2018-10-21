package org.ssunion.cloudschedule.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.domain.telegram.pushbot.User;

import java.util.List;

/**
 * @author kasad0r
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUserToken(long userToken);

    List<User> findUsersBySettings_SelectedGroupAndSettings_AdminNoticeFalse(String group);
}
