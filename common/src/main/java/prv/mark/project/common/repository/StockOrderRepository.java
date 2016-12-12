package prv.mark.project.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for {@link prv.mark.project.common.entity.StockOrderEntity} entities.
 *
 * @author mlglenn.
 */
@Repository
public interface StockOrderRepository extends JpaRepository<prv.mark.project.common.entity.StockOrderEntity, Long> {

    Optional<prv.mark.project.common.entity.StockOrderEntity> findById(Long id);

    List<prv.mark.project.common.entity.StockOrderEntity> findByOrderDate(Date orderDate);

    List<prv.mark.project.common.entity.StockOrderEntity> findByOrderType(String orderType);

    List<prv.mark.project.common.entity.StockOrderEntity> findByOrderStatus(String orderStatus);

    List<prv.mark.project.common.entity.StockOrderEntity> findAll();
}
