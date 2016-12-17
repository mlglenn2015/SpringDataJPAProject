package prv.mark.project.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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

    //@Query("select u from UsersEntity u where u.userName = ?1")
    Optional<UsersEntity> findByUserId(String userId);

    List<UsersEntity> findAll();
}
