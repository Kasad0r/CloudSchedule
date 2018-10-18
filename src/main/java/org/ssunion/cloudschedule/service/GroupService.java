package org.ssunion.cloudschedule.service;

import org.ssunion.cloudschedule.domain.Group;

import java.util.List;

public interface GroupService {
    void addGroup(Group group);

    Group getByName(String name);

    void editGroup(Group group);

    void delete(Group group);

    void delete(long id);

    List<Group> getAll();
    List<Group> getByCource(String course);
}
