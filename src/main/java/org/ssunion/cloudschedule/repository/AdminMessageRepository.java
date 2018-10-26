package org.ssunion.cloudschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.telegram.adminbot.domain.AdminMessage;

/**
 * @author kasad0r
 */
public interface AdminMessageRepository extends JpaRepository<AdminMessage, Long> {

}
