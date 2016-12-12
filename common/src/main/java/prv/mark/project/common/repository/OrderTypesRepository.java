package prv.mark.project.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import prv.mark.project.common.entity.OrderTypesEntity;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for {@link prv.mark.project.common.entity.OrderTypesEntity} entities.
 *
 * @author mlglenn.
 */
@Repository
public interface OrderTypesRepository extends JpaRepository<OrderTypesEntity, Long> {

    Optional<OrderTypesEntity> findById(Long id);

    @Query("select o from OrderTypesEntity o where o.orderType = ?1")
    Optional<prv.mark.project.common.entity.OrderTypesEntity> findByOrderType(String orderType);

    List<OrderTypesEntity> findAll();
}
