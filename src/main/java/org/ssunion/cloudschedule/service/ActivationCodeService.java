package org.ssunion.cloudschedule.service;

import org.ssunion.cloudschedule.telegram.adminbot.domain.ActivationCode;

import java.util.List;

/**
 * @author kasad0r
 */
public interface ActivationCodeService {
    ActivationCode getByUUID(String UUID);

    ActivationCode addActivationCode(ActivationCode code);

    ActivationCode editActivationCode(ActivationCode code);

    List<ActivationCode> getAll();

    void deleteActivationCode(ActivationCode code);

}
