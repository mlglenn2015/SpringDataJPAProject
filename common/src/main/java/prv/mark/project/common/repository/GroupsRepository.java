package prv.mark.project.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prv.mark.project.common.entity.GroupsEntity;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for {@link prv.mark.project.common.entity.GroupsEntity} entities.
 *
 * @author mlglenn.
 */
@Repository
public interface GroupsRepository extends JpaRepository<GroupsEntity, Long> {

    //Optional<GroupsEntity> findById(String id);

    //@Query("select g from GroupsEntity g where g.groupId = ?1")
    Optional<GroupsEntity> findByGroupId(String groupId);

    List<GroupsEntity> findAll();
}
