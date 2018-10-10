package org.ssunion.cloudschedule.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.ssunion.cloudschedule.domain.Group;

public interface GroupRepo extends JpaRepository<Group, Long> {
}
