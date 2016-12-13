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
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

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
    public void testInsertGroups() {
        GroupsEntity entity = buildGroupEntity("10000");
        assertNotNull(entity);

        GroupsEntity savedEntity = insertEntity(entity);
        assertNotNull(savedEntity);
        assertTrue(savedEntity.getGroupName().equals("TEST GROUP" + savedEntity.getGroupId()));

        Optional<GroupsEntity> newEntity = groupsRepository.findByGroupId(savedEntity.getGroupId());
        assertNotNull(newEntity);

        assertEquals(newEntity.get().getGroupName(), "TEST GROUP" + newEntity.get().getGroupId());
        assertEquals(savedEntity.getGroupName(), newEntity.get().getGroupName());
    }

    @Test
    public void testFindByGroupId() {
        Optional<GroupsEntity> entity = groupsRepository.findByGroupId("00001");
        assertTrue(StringUtils.isNotEmpty(entity.get().getGroupName()));
        assertEquals(entity.get().getGroupName(), "Administrators");
    }

    @Test
    public void testFindByInvalidGroup() {
        Optional<GroupsEntity> group = groupsRepository.findByGroupId("ZZZZZZZ");
        assertEquals(Optional.ofNullable(group).get(), Optional.empty());
    }

    /*@Test TODO
    public void testFindAll() {
        Optional<GroupsEntity> group = groupsRepository.findByGroupId("Administrators");
        assertTrue(Optional.ofNullable(group.get().getGroupId()).isPresent());
        assertEquals(group.get().getGroupId(), "00001");
    }*/

    private GroupsEntity buildGroupEntity(final String id) {
        prv.mark.project.common.entity.GroupsEntity entity = new prv.mark.project.common.entity.GroupsEntity();
        entity.setGroupId(id);
        entity.setGroupName("TEST GROUP" + entity.getGroupId());

        Set<UsersEntity> usersEntitySet = new HashSet<>();
        UsersEntity usersEntity = new UsersEntity();

        usersEntity.setUserId("X10000");
        usersEntity.setUserName("Test User");  //TODO add more users to this group
        usersEntitySet.add(usersEntity);

        entity.setUsers(usersEntitySet);
        return entity;
    }

    private GroupsEntity insertEntity(final GroupsEntity entity) {

        LOGGER.debug("GroupsRepositoryTests.insertEntity()");
        GroupsEntity returnEntity = new GroupsEntity();
        try {
            returnEntity = groupsRepository.saveAndFlush(entity);

        } catch (PersistenceException | JpaSystemException | NoSuchElementException e) {
            String msg = "Exception caught while saving GroupsEntity entity " + entity.getGroupId() + ".";

            ExceptionRouter.logAndThrowApplicationException(LOGGER, msg, e.toString());
        }
        LOGGER.debug("*** Saved GroupsEntity entity ***");
        LOGGER.debug(returnEntity.toString());

        return returnEntity;
    }
}
