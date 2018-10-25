package org.ssunion.cloudschedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssunion.cloudschedule.domain.base.Group;
import org.ssunion.cloudschedule.repository.GroupRepository;
import org.ssunion.cloudschedule.service.GroupService;

import java.util.List;

/**
 * @author kasad0r
 */
@Service
//@Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Override
    public void addGroup(Group group) {
        groupRepository.saveAndFlush(group);
    }

    @Override
    public Group getByName(String name) {
        return groupRepository.findByGroupName(name);
    }

    @Override
    public void editGroup(Group group) {
        groupRepository.saveAndFlush(group);
    }

    @Override
    public void delete(Group group) {
        groupRepository.delete(group);
    }

    @Override
    @Deprecated
    public void delete(long id) {

    }

    @Override
    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getByCource(String course) {
        return groupRepository.findByGroupNameStartingWith(course);
    }
}
