package org.ssunion.cloudschedule.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.domain.telegram.pushbot.User;

/**
 * @author kasad0r
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUserToken(long userToken);
}
