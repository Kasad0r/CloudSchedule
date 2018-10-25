package org.ssunion.cloudschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.telegram.adminbot.domain.Admin;

/**
 * @author kasad0r
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findFirstByUserToken(long token);
}
