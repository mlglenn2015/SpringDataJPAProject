package prv.mark.project.common.repository;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import prv.mark.project.common.entity.UsersEntity;
import prv.mark.project.common.exception.ExceptionRouter;
import prv.mark.project.common.util.StringUtils;
import prv.mark.project.testutils.junit.AbstractAppTransactionalTest;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * JUnit tests for the {@link UsersRepository}.
 *
 * @author mlglenn
 */
public class UsersRepositoryTests extends AbstractAppTransactionalTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersRepositoryTests.class);

    @Autowired
    private UsersRepository usersRepository;

    @Before
    public void setUp() {
        assertNotNull(usersRepository);
    }

    @Test
    public void defaultTest() {
        LOGGER.debug("UsersRepositoryTests.defaultTest()");
    }

    @Test
    public void testUsersRepository() {
        prv.mark.project.common.entity.UsersEntity entity = buildEntity();
        entity.setId(100L);
        assertNotNull(entity);

        prv.mark.project.common.entity.UsersEntity savedEntity = insertEntity(entity);
        assertNotNull(savedEntity);
        assertTrue(savedEntity.getId() > 0);

        Optional<UsersEntity> newEntity = usersRepository.findById(savedEntity.getId());
        assertNotNull(newEntity);

        assertEquals(newEntity.get().getUserName(), "APPLICATION TESTING");
        assertEquals(savedEntity.getUserName(), newEntity.get().getUserName());
    }

    @Test
    public void testFindById() {
        Optional<UsersEntity> orderType = usersRepository.findById(1L);
        assertTrue(StringUtils.isNotEmpty(orderType.get().getUserName()));
    }

    @Test
    public void testFindByUser() {
        List<UsersEntity> user = usersRepository.findByUserName("Mark User1");
        assertTrue(user.size() > 0);
    }

    @Test
    public void testFindByInvalidUser() {
        List<UsersEntity> user = usersRepository.findByUserName("TEST");
        assertTrue(user.size() == 0);
    }


    private prv.mark.project.common.entity.UsersEntity buildEntity() {
        prv.mark.project.common.entity.UsersEntity entity = new prv.mark.project.common.entity.UsersEntity();
        entity.setId(null);
        entity.setUserName("APPLICATION TESTING");
        entity.setGroupId(1L);
        return entity;
    }

    private prv.mark.project.common.entity.UsersEntity insertEntity(
            final prv.mark.project.common.entity.UsersEntity entity) {

        LOGGER.debug("UsersRepositoryTests.insertEntity()");
        prv.mark.project.common.entity.UsersEntity returnEntity = new prv.mark.project.common.entity.UsersEntity();
        try {
            returnEntity = usersRepository.saveAndFlush(entity);

        } catch (PersistenceException | JpaSystemException | NoSuchElementException e) {
            String msg = "Exception caught while saving UsersEntity entity " + entity.getId() + ".";

            ExceptionRouter.logAndThrowApplicationException(LOGGER, msg, e.toString());
        }
        LOGGER.debug("*** Saved UsersEntity entity ***");
        LOGGER.debug(returnEntity.toString());

        return returnEntity;
    }
}
