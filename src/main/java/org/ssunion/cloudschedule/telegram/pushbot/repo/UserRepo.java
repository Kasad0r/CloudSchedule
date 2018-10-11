package org.ssunion.cloudschedule.telegram.pushbot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.telegram.pushbot.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserToken(long token);

    boolean existsUserByUserToken(long token);
}
