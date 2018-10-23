package org.ssunion.cloudschedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssunion.cloudschedule.repo.AdminRepository;
import org.ssunion.cloudschedule.service.AdminService;
import org.ssunion.cloudschedule.telegram.adminbot.domain.Admin;

import java.util.List;

/**
 * @author kasad0r
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final
    AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.saveAndFlush(admin);
    }

    @Override
    public void delete(Admin admin) {
        adminRepository.delete(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return adminRepository.saveAndFlush(admin);
    }

    @Override
    public Admin getByToken(long token) {
        return adminRepository.findFirstByUserToken(token);
    }
}
