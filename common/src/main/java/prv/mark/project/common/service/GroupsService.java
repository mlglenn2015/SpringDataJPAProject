package prv.mark.project.common.service;

import prv.mark.project.common.entity.GroupsEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for {@link GroupsEntity} entities.
 *
 * @author mlglenn.
 */
public interface GroupsService {

    Optional<GroupsEntity> findByGroupId(String groupId);

    List<GroupsEntity> findAll();

    GroupsEntity save(GroupsEntity entity);
}
