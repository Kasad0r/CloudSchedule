package org.ssunion.cloudschedule.service;

import org.ssunion.cloudschedule.telegram.adminbot.domain.AdminMessage;

/**
 * @author kasad0r
 */
public interface AdminMessageService {
    AdminMessage addAdminMessage(AdminMessage message);

    AdminMessage deleteAdminMessage(AdminMessage message);

    AdminMessage getAll(AdminMessage message);

    AdminMessage getCurrentMessage(long currentMessage);
}
