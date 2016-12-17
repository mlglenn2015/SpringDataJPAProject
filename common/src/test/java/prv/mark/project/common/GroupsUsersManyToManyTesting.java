package prv.mark.project.common;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import prv.mark.project.common.entity.GroupsEntity;
import prv.mark.project.common.entity.UsersEntity;
import prv.mark.project.common.repository.GroupsRepository;
import prv.mark.project.common.repository.UsersRepository;
import prv.mark.project.testutils.config.TestUtilConfig;
import prv.mark.project.testutils.junit.AbstractAppTransactionalTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for the many-to-many Groups and Users.
 */
@ContextConfiguration(classes = {TestUtilConfig.class}, initializers = ConfigFileApplicationContextInitializer.class)
public class GroupsUsersManyToManyTesting extends AbstractAppTransactionalTest implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupsUsersManyToManyTesting.class);

    @Autowired
    private GroupsRepository groupsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ApplicationContext applicationContext;


    @Override
    @Before
    public void setUp() {
        LOGGER.debug("GroupsUsersManyToManyTesting.setUp(): -----> CREATE <-----");
        LOGGER.debug("groupsRepository:{}", groupsRepository);
        LOGGER.debug("usersRepository:{}", usersRepository);
        LOGGER.debug("applicationContext:{}", applicationContext);
        assertNotNull(groupsRepository);
        assertNotNull(usersRepository);
        assertNotNull(applicationContext);
        LOGGER.debug("applicationContext.getBeanDefinitionNames(): {}", applicationContext.getBeanDefinitionNames());
    }

    @Override
    @After
    public void tearDown() {
        LOGGER.debug("GroupsUsersManyToManyTesting.tearDown(): -----> DESTROY <-----");
    }

    @Test
    public void defaultTest() {
        LOGGER.debug("GroupsUsersManyToManyTesting.defaultTest()");
    }

    @Test
    public void testGroupWithUser100(){

        GroupsEntity groupsEntity = buildGroupsEntity("G10100", "TEST GROUP100");
        UsersEntity usersEntity = buildUsersEntity("U10100", "Test User100");

        printGroupsEntity(groupsEntity);
        printUsersEntity(usersEntity);

        GroupsEntity savedGroupsEntity = saveGroupsEntity(groupsEntity);
        UsersEntity savedUsersEntity = saveUsersEntity(usersEntity);

        assertNotNull(savedGroupsEntity);
        assertEquals(groupsEntity.getGroupName(), savedGroupsEntity.getGroupName());

        assertNotNull(savedUsersEntity);
        assertEquals(usersEntity.getUserName(), savedUsersEntity.getUserName());

        printGroupsEntity(savedGroupsEntity);
        printUsersEntity(savedUsersEntity);
    }


    private GroupsEntity buildGroupsEntity(final String id, final String name){
        return new GroupsEntity(id, name+"_"+id);
    }

    private UsersEntity buildUsersEntity(final String id, final String name){
        return new UsersEntity(id, name+"_"+id);
    }

    private GroupsEntity saveGroupsEntity(final GroupsEntity record) {
        return groupsRepository.save(record);
    }

    private UsersEntity saveUsersEntity(final UsersEntity record) {
        return usersRepository.save(record);
    }

    private void printGroupsEntity(final GroupsEntity entity) {
        LOGGER.info("*** GroupsUsersManyToManyTesting: LISTING GROUPS ENTITY ***");
        LOGGER.info("GroupId: {}, GroupName: {}", entity.getGroupId(), entity.getGroupName());
    }

    private void printUsersEntity(final UsersEntity entity) {
        LOGGER.info("   *** GroupsUsersManyToManyTesting: LISTING USERS ENTITY ***");
        LOGGER.info("   UserId: {}, UserName: {}", entity.getUserId(), entity.getUserName());
    }

}