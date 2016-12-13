package prv.mark.project.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prv.mark.project.common.entity.UsersEntity;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for {@link prv.mark.project.common.entity.UsersEntity} entities.
 *
 * @author mlglenn.
 */
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findById(Long id);

    @Query("select u from UsersEntity u where u.userName = ?1")
    List<UsersEntity> findByUserName(String userName);

    @Query("select u from UsersEntity u where u.groupId = ?1")
    List<UsersEntity> findByGroupId(Long groupId);

    List<UsersEntity> findAll();

}
