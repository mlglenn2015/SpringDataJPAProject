package prv.mark.project.common.repository;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import prv.mark.project.common.entity.OrderTypesEntity;
import prv.mark.project.common.exception.ExceptionRouter;
import prv.mark.project.common.util.StringUtils;
import prv.mark.project.testutils.junit.AbstractAppTransactionalTest;

import javax.persistence.PersistenceException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * JUnit tests for the {@link OrderTypesRepository}.
 *
 * @author mlglenn
 */
public class OrderTypesRepositoryTests extends AbstractAppTransactionalTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderTypesRepositoryTests.class);

    @Autowired
    private OrderTypesRepository orderTypesRepository;

    @Before
    public void setUp() {
        assertNotNull(orderTypesRepository);
    }

    @Test
    public void defaultTest() {
        LOGGER.debug("OrderTypesRepositoryTests.defaultTest()");
    }

    @Test
    public void testOrderTypesRepository() {
        prv.mark.project.common.entity.OrderTypesEntity entity = buildEntity();
        assertNotNull(entity);

        prv.mark.project.common.entity.OrderTypesEntity savedEntity = insertEntity(entity);
        assertNotNull(savedEntity);
        assertTrue(savedEntity.getId() > 0);

        Optional<OrderTypesEntity> newEntity
                = orderTypesRepository.findById(savedEntity.getId());
        assertNotNull(newEntity);

        assertEquals(newEntity.get().getDescription(), "ORDER IN TESTING STATUS");
        assertEquals(savedEntity.getOrderType(), newEntity.get().getOrderType());
    }

    @Test
    public void testFindById() {
        Optional<OrderTypesEntity> orderType = orderTypesRepository.findById(1L);
        assertTrue(StringUtils.isNotEmpty(orderType.get().getOrderType()));
    }

    @Test
    public void testFindByOrderType() {
        Optional<OrderTypesEntity> orderType = orderTypesRepository.findByOrderType("LIMIT ORDER");
        assertTrue(StringUtils.isNotEmpty(orderType.get().getDescription()));
        assertEquals(orderType.get().getDescription(), "ORDER PLACED AT MAX LIMIT ORDER PRICE");
    }

    @Test
    public void testFindByInvalidOrderType() {
        Optional<OrderTypesEntity> orderType = orderTypesRepository.findByOrderType("TEST");
        assertEquals(orderType, Optional.empty());
    }


    private prv.mark.project.common.entity.OrderTypesEntity buildEntity() {
        prv.mark.project.common.entity.OrderTypesEntity entity = new prv.mark.project.common.entity.OrderTypesEntity();
        entity.setId(null);
        entity.setOrderType("TESTING");
        entity.setDescription("ORDER IN TESTING STATUS");
        return entity;
    }

    private prv.mark.project.common.entity.OrderTypesEntity insertEntity(
            final prv.mark.project.common.entity.OrderTypesEntity entity) {

        LOGGER.debug("OrderStatusRepositoryTests.insertEntity()");
        prv.mark.project.common.entity.OrderTypesEntity returnEntity = new prv.mark.project.common.entity.OrderTypesEntity();
        try {
            returnEntity = orderTypesRepository.saveAndFlush(entity);

        } catch (PersistenceException | JpaSystemException | NoSuchElementException e) {
            String msg = "Exception caught while saving OrderTypes entity " + entity.getId() + ".";

            ExceptionRouter.logAndThrowApplicationException(LOGGER, msg, e.toString());
        }
        LOGGER.debug("*** Saved OrderTypes entity ***");
        LOGGER.debug(returnEntity.toString());

        return returnEntity;
    }

}
