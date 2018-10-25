package org.ssunion.cloudschedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.telegram.adminbot.domain.ActivationCode;

/**
 * @author kasad0r
 */
public interface ActivationCodeRepository extends JpaRepository<ActivationCode, Long> {

    ActivationCode findActivationCodeByUuid(String uuid);

}
