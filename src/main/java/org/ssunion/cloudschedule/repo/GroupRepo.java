package org.ssunion.cloudschedule.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ssunion.cloudschedule.domain.Group;

import java.util.List;

public interface GroupRepo extends JpaRepository<Group, Long> {
    List<Group> findByGroupNameStartingWith(String startWith);
    Group findByGroupName(String groupname);
}
