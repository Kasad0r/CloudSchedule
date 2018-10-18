package org.ssunion.cloudschedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssunion.cloudschedule.domain.Group;
import org.ssunion.cloudschedule.repo.GroupRepo;
import org.ssunion.cloudschedule.service.GroupService;

import java.util.List;
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepo groupRepo;

    @Override
    public void addGroup(Group group) {
        groupRepo.saveAndFlush(group);
    }

    @Override
    public Group getByName(String name) {
        return groupRepo.findByGroupName(name);
    }

    @Override
    public void editGroup(Group group) {
        groupRepo.saveAndFlush(group);
    }

    @Override
    public void delete(Group group) {
        groupRepo.delete(group);
    }

    @Override
    @Deprecated
    public void delete(long id) {

    }

    @Override
    public List<Group> getAll() {
        return groupRepo.findAll();
    }

    @Override
    public List<Group> getByCource(String course) {
        return groupRepo.findByGroupNameStartingWith(course);
    }
}
