package org.ssunion.cloudschedule.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.domain.base.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByGroupNameStartingWith(String startWith);

    Group findByGroupName(String groupName);
}
