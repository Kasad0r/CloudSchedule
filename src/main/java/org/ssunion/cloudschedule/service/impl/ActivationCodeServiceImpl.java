package org.ssunion.cloudschedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssunion.cloudschedule.repository.ActivationCodeRepository;
import org.ssunion.cloudschedule.service.ActivationCodeService;
import org.ssunion.cloudschedule.telegram.adminbot.domain.ActivationCode;

import java.util.List;

/**
 * @author kasad0r
 */
@Service
public class ActivationCodeServiceImpl implements ActivationCodeService {

    private ActivationCodeRepository activationCodeRepository;

    @Autowired
    public void setActivationCodeRepository(ActivationCodeRepository activationCodeRepository) {
        this.activationCodeRepository = activationCodeRepository;
    }

    @Override
    public ActivationCode getByUUID(String UUID) {
        return activationCodeRepository.findActivationCodeByUuid(UUID);
    }

    @Override
    public ActivationCode addActivationCode(ActivationCode code) {
        return activationCodeRepository.saveAndFlush(code);
    }

    @Override
    public ActivationCode editActivationCode(ActivationCode code) {
        return activationCodeRepository.saveAndFlush(code);
    }

    @Override
    public List<ActivationCode> getAll() {
        return activationCodeRepository.findAll();
    }

    @Override
    public void deleteActivationCode(ActivationCode code) {
        activationCodeRepository.delete(code);
    }
}
