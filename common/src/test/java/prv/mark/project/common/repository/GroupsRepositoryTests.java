package prv.mark.project.common.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.CollectionUtils;
import prv.mark.project.common.entity.GroupsEntity;
import prv.mark.project.common.entity.UsersEntity;
import prv.mark.project.common.exception.ExceptionRouter;
import prv.mark.project.common.util.StringUtils;
import prv.mark.project.testutils.config.TestUtilConfig;
import prv.mark.project.testutils.junit.AbstractAppTransactionalTest;

import javax.persistence.PersistenceException;
import java.util.HashSet;
import java.util.List;
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
@ContextConfiguration(classes = {TestUtilConfig.class}, initializers = ConfigFileApplicationContextInitializer.class)
public class GroupsRepositoryTests extends AbstractAppTransactionalTest implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupsRepositoryTests.class);

    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private ApplicationContext applicationContext;


    @Override
    @Before
    public void setUp() {
        LOGGER.debug("GroupsRepositoryTests.setUp(): -----> CREATE <-----");
        LOGGER.debug("groupsRepository:{}", groupsRepository);
        LOGGER.debug("applicationContext:{}", applicationContext);
        assertNotNull(groupsRepository);
        assertNotNull(applicationContext);
        LOGGER.debug("applicationContext.getBeanDefinitionNames(): {}", applicationContext.getBeanDefinitionNames());
    }

    @Override
    @After
    public void tearDown() {
        LOGGER.debug("GroupsRepositoryTests.tearDown(): -----> DESTROY <-----");
    }

    @Test
    public void defaultTest() {
        LOGGER.debug("GroupsRepositoryTests.defaultTest()");
    }

    @Test
    public void testInsertGroups() {
        LOGGER.debug("************************** BEGIN GroupsRepositoryTests.testInsertGroups() *********************************");

        LOGGER.debug("SAVING GROUPSENTITY WITH VALUES G10001, TEST GROUP1, AND USERS U10000, U10002, U10003, U10004, U10005");
        groupsRepository.saveAndFlush(buildGroupsEntity("G10001", "TEST GROUP1"));
        LOGGER.debug("");
        LOGGER.debug("LISTING GROUPS TABLE...");
        for (GroupsEntity groupsEntity : groupsRepository.findAll()) {
            LOGGER.debug(groupsEntity.toString());
        }
        LOGGER.debug("");

        groupsRepository.save(buildGroupsEntity("G10002", "TEST GROUP2"));
        groupsRepository.save(buildGroupsEntity("G10003", "TEST GROUP3"));
        groupsRepository.save(buildGroupsEntity("G10004", "TEST GROUP4"));
        groupsRepository.save(buildGroupsEntity("G10005", "TEST GROUP5"));
        groupsRepository.save(buildGroupsEntity("G10006", "TEST GROUP6"));

        // fetch all groups
        LOGGER.debug("Groups found with findAll():");
        LOGGER.debug("-------------------------------");
        for (GroupsEntity groupsEntity : groupsRepository.findAll()) {
            LOGGER.debug(groupsEntity.toString());
        }
        LOGGER.debug("");

        //Fetch specific groups
        LOGGER.debug("Group found with findByGroupId(G10004):");
        Optional<GroupsEntity> groupsEntity = groupsRepository.findByGroupId("G10004");
        assertNotNull(groupsEntity.get());
        assertEquals(groupsEntity.get().getGroupId(), "G10004");
        printGroupsEntity(groupsEntity.get());

        testFindAll();
        LOGGER.debug("************************** END GroupsRepositoryTests.testInsertGroups() *********************************");
    }

    //TODO bi-directional many-to-many testing


    @Test
    public void testFindByGroupId() {
        Optional<GroupsEntity> entity = groupsRepository.findByGroupId("00001");
        assertTrue(StringUtils.isNotEmpty(entity.get().getGroupName()));
        assertEquals(entity.get().getGroupName(), "Administrators");

        printGroupsEntity(entity.get());
    }

    @Test
    public void testFindByInvalidGroup() {
        Optional<GroupsEntity> group = groupsRepository.findByGroupId("ZZZZZZZ");
        assertEquals(Optional.ofNullable(group).get(), Optional.empty());
    }

    @Test
    public void testFindAll() {
        List<GroupsEntity> group = groupsRepository.findAll();
        assertNotNull(group);
        assertTrue(group.size() > 0);

        listGroups(group);
    }



    private void listGroups(final List<GroupsEntity> list) {
        Set<GroupsEntity> set = new HashSet<>(list);
        if (!CollectionUtils.isEmpty(set)) {
            for (GroupsEntity entity : set) {
                printGroupsEntity(entity);
            }
        }
    }

    private GroupsEntity buildGroupsEntity(final String id, final String name) {

        return new GroupsEntity(id, name+"_"+id, buildUsersEntity());
    }

    private Set<UsersEntity> buildUsersEntity() {
        Set<UsersEntity> usersEntitySet = new HashSet<>();
        usersEntitySet.add(new UsersEntity("U10000", "Test User_U10000", null));
        usersEntitySet.add(new UsersEntity("U10001", "Test User_U10001", null));
        usersEntitySet.add(new UsersEntity("U10002", "Test User_U10002", null));
        usersEntitySet.add(new UsersEntity("U10003", "Test User_U10003", null));
        usersEntitySet.add(new UsersEntity("U10004", "Test User_U10004", null));
        usersEntitySet.add(new UsersEntity("U10005", "Test User_U10005", null));
        return usersEntitySet;
    }

    private GroupsEntity insertEntity(final GroupsEntity entity) {

        LOGGER.debug("GroupsRepositoryTests.insertEntity()");
        try {
            GroupsEntity returnEntity = groupsRepository.saveAndFlush(entity);
            LOGGER.debug("*** Saved GroupsEntity entity ***");
            LOGGER.debug(returnEntity.toString());
            return returnEntity;

        } catch (PersistenceException | JpaSystemException | NoSuchElementException e) {
            String msg = "Exception caught while saving GroupsEntity entity " + entity.getGroupId() + ".";
            ExceptionRouter.logAndThrowApplicationException(LOGGER, msg, e.toString());
        }
        return null;
    }

    private void printGroupsEntity(final GroupsEntity entity) {
        LOGGER.info("*** LISTING GROUPS ENTITY ***");
        LOGGER.info("GroupId: {}", entity.getGroupId());
        LOGGER.info("GroupName: {}", entity.getGroupName());

        if (!CollectionUtils.isEmpty(entity.getUsersCollection())) {
            for (UsersEntity user : entity.getUsersCollection()) {
                printUsersEntity(user);
            }
        }
    }

    private void printUsersEntity(final UsersEntity entity) {
        LOGGER.info("   *** LISTING USERS ENTITY ***");
        LOGGER.info("   UserId: {}", entity.getUserId());
        LOGGER.info("   UserName: {}", entity.getUserName());

        /*if (!CollectionUtils.isEmpty(entity.getGroups())) {
            for (GroupsEntity group : entity.getGroups()) {
                printGroupsEntity(group);
            }
        }*/
    }

}
