package org.ssunion.cloudschedule.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.telegram.adminbot.domain.ActivationCode;

/**
 * @author kasad0r
 */
public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {
    /**
     * @param UUID
     * @return
     */
    ActivationCode findFirstByUUID(String UUID);
}
