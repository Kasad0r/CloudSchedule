package org.ssunion.cloudschedule.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.telegram.adminbot.domain.Admin;

/**
 * @author kasad0r
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin getAdminByUserToken(long token);
}
