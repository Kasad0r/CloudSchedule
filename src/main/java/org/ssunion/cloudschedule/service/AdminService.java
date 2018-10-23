package org.ssunion.cloudschedule.service;

import org.ssunion.cloudschedule.telegram.adminbot.domain.Admin;

import java.util.List;

public interface AdminService {
    Admin addAdmin(Admin admin);

    void delete(Admin admin);

    List<Admin> getAll();

    Admin updateAdmin(Admin admin);

    Admin getByToken(long token);
}
