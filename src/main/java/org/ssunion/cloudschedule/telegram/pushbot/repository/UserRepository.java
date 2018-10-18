package org.ssunion.cloudschedule.telegram.pushbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.telegram.pushbot.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
