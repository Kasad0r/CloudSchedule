package org.ssunion.cloudschedule.telegram.admin.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.telegram.admin.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserToken(long token);


}
