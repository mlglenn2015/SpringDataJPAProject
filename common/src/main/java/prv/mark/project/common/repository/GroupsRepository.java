package prv.mark.project.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    Optional<GroupsEntity> findById(Long id);

    @Query("select g from GroupsEntity g where g.groupName = ?1")
    List<GroupsEntity> findByGroupName(String groupName);

    @Query("select g from GroupsEntity g where g.userId = ?1")
    List<GroupsEntity> findByUserId(Long userId);

    List<GroupsEntity> findAll();
}
