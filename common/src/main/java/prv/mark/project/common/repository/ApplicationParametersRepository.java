package prv.mark.project.common.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prv.mark.project.common.entity.ApplicationParametersEntity;

/**
 * DAO Interface for {@link ApplicationParametersEntity} entities.
 *
 * @author mlglenn.
 */
@Repository
public interface ApplicationParametersRepository extends JpaRepository<ApplicationParametersEntity, Long> {

    /**
     * Returns the property by the parameter key (not to be confused by id).
     *
     * @param key {@link String}
     * @return {@link ApplicationParametersEntity}
     */
    @Cacheable("parameters")
    ApplicationParametersEntity findByKey(String key);

    /**
     * Returns the active property by the parameter key (not to be confused by id).
     *
     * @param key {@link String}
     * @param active {@link Boolean}
     * @return {@link ApplicationParametersEntity}
     */
    @Cacheable("parameters")
    ApplicationParametersEntity findActiveByKey(String key, Boolean active);

}
