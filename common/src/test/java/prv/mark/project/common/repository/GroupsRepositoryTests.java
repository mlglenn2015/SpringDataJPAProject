package prv.mark.project.common.repository;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import prv.mark.project.common.entity.GroupsEntity;
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
 * JUnit tests for the {@link GroupsRepository}.
 *
 * @author mlglenn
 */
public class GroupsRepositoryTests extends AbstractAppTransactionalTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupsRepositoryTests.class);

    @Autowired
    private GroupsRepository groupsRepository;

    @Before
    public void setUp() {
        assertNotNull(groupsRepository);
    }

    @Test
    public void defaultTest() {
        LOGGER.debug("GroupsRepositoryTests.defaultTest()");
    }

    @Test
    public void testGroupsRepository() {
        prv.mark.project.common.entity.GroupsEntity entity = buildEntity();
        entity.setId(100L);
        assertNotNull(entity);

        prv.mark.project.common.entity.GroupsEntity savedEntity = insertEntity(entity);
        assertNotNull(savedEntity);
        assertTrue(savedEntity.getId() > 0);

        Optional<GroupsEntity> newEntity = groupsRepository.findById(savedEntity.getId());
        assertNotNull(newEntity);

        assertEquals(newEntity.get().getGroupName(), "TEST GROUP");
        assertEquals(savedEntity.getGroupName(), newEntity.get().getGroupName());
    }

    @Test
    public void testFindById() {
        Optional<GroupsEntity> orderType = groupsRepository.findById(1L);
        assertTrue(StringUtils.isNotEmpty(orderType.get().getGroupName()));
    }

    @Test
    public void testFindByGroup() {
        List<GroupsEntity> user = groupsRepository.findByGroupName("Administrators");
        assertTrue(user.size() > 0);
    }

    @Test
    public void testFindByInvalidGroup() {
        List<GroupsEntity> user = groupsRepository.findByGroupName("ZZZZZZZ");
        assertTrue(user.size() == 0);
    }


    private prv.mark.project.common.entity.GroupsEntity buildEntity() {
        prv.mark.project.common.entity.GroupsEntity entity = new prv.mark.project.common.entity.GroupsEntity();
        entity.setId(null);
        entity.setGroupName("TEST GROUP");
        entity.setUserId(1L);
        return entity;
    }

    private prv.mark.project.common.entity.GroupsEntity insertEntity(
            final prv.mark.project.common.entity.GroupsEntity entity) {

        LOGGER.debug("UsersRepositoryTests.insertEntity()");
        prv.mark.project.common.entity.GroupsEntity returnEntity = new prv.mark.project.common.entity.GroupsEntity();
        try {
            returnEntity = groupsRepository.saveAndFlush(entity);

        } catch (PersistenceException | JpaSystemException | NoSuchElementException e) {
            String msg = "Exception caught while saving GroupsEntity entity " + entity.getId() + ".";

            ExceptionRouter.logAndThrowApplicationException(LOGGER, msg, e.toString());
        }
        LOGGER.debug("*** Saved GroupsEntity entity ***");
        LOGGER.debug(returnEntity.toString());

        return returnEntity;
    }
}
