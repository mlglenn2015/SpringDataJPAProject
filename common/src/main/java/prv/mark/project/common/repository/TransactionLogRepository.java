package prv.mark.project.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prv.mark.project.common.entity.TransactionLogEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for {@link prv.mark.project.common.entity.TransactionLogEntity} entities.
 *
 * @author mlglenn.
 */
@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLogEntity, Long> {

    Optional<TransactionLogEntity> findById(Long id);

    List<prv.mark.project.common.entity.TransactionLogEntity> findByLogDateTime(Date logDateTime);

    List<prv.mark.project.common.entity.TransactionLogEntity> findByTransactionType(String transactionType);

    List<TransactionLogEntity> findAll();
}
