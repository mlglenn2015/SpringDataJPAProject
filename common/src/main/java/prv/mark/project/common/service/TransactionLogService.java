package prv.mark.project.common.service;

import prv.mark.project.common.entity.TransactionLogEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for {@link TransactionLogEntity} entities.
 *
 * @author mlglenn.
 */
public interface TransactionLogService {

    Optional<TransactionLogEntity> findById(Long id);

    List<TransactionLogEntity> findByLogDateTime(Date logDateTime);

    List<prv.mark.project.common.entity.TransactionLogEntity> findByTransactionType(String transactionType);

    List<TransactionLogEntity> findAll();

    TransactionLogEntity save(TransactionLogEntity transactionLog);
}
