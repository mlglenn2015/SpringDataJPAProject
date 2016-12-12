package prv.mark.project.common.service;

import prv.mark.project.common.entity.StockOrderEntity;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for {@link StockOrderEntity} entities.
 *
 * @author mlglenn.
 */
public interface StockOrderService {

    Optional<prv.mark.project.common.entity.StockOrderEntity> findById(Long id);

    //List<prv.mark.project.common.entity.StockOrder> findByOrderDate(Date orderDate);

    //List<prv.mark.project.common.entity.StockOrder> findByOrderType(String orderType);

    List<prv.mark.project.common.entity.StockOrderEntity> findByOrderStatus(String orderStatus);

    List<prv.mark.project.common.entity.StockOrderEntity> findAll();

    StockOrderEntity save(StockOrderEntity order);
}
