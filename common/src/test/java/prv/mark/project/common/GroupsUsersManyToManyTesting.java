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
import org.springframework.util.CollectionUtils;
import prv.mark.project.common.entity.GroupsEntity;
import prv.mark.project.common.entity.UsersEntity;
import prv.mark.project.common.repository.GroupsRepository;
import prv.mark.project.common.repository.UsersRepository;
import prv.mark.project.testutils.config.TestUtilConfig;
import prv.mark.project.testutils.junit.AbstractAppTransactionalTest;

import java.util.HashSet;
import java.util.Set;

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
    public void testGroupWithUserU10100AndU10101(){
        LOGGER.debug("**********************************************************************************************************************");
        LOGGER.debug("************************** BEGIN GroupsUsersManyToManyTesting.testGroupWithUser100() *********************************");
        GroupsEntity groupsEntity = buildGroupsEntity("G10100", "TEST GROUP100");
        LOGGER.debug("SAVING GROUPSENTITY WITH VALUES G10100, TEST GROUP100");
        GroupsEntity savedGroupsEntity = saveGroupsEntity(groupsEntity);
        assertNotNull(savedGroupsEntity);
        assertEquals(groupsEntity.getGroupName(), savedGroupsEntity.getGroupName());
        LOGGER.debug("");
        printGroupsEntity(savedGroupsEntity);
        LOGGER.debug("************************************************************************************************");
        LOGGER.debug("LISTING GROUPS TABLE...");
        for (GroupsEntity gEntity : groupsRepository.findAll()) {
            LOGGER.debug(gEntity.toString());
        }
        LOGGER.debug("");
        LOGGER.debug("LISTING USERS TABLE FOR U10100 AND U10101... WE EXPECT THE GROUP ID TO BE G10100");
        LOGGER.debug("");
        printUsersEntity(usersRepository.findByUserId("U10100").get());
        LOGGER.debug("");
        printUsersEntity(usersRepository.findByUserId("U10101").get());
        LOGGER.debug("");

        //TODO list the USER_GROUP entries

        LOGGER.debug("************************************************************************************************");



        //UsersEntity usersEntity = buildUsersEntity("U10100", "Test User100");
        //printUsersEntity(usersEntity);
        //LOGGER.debug("");
        //LOGGER.debug("SAVING USERSENTITY WITH VALUES U10100, Test User100");
        //UsersEntity savedUsersEntity = saveUsersEntity(usersEntity);
        //LOGGER.debug("************************************************************************************************");



        //assertNotNull(savedUsersEntity);
        //assertEquals(usersEntity.getUserName(), savedUsersEntity.getUserName());

        //printGroupsEntity(savedGroupsEntity);
        //printUsersEntity(savedUsersEntity);
    }


    private GroupsEntity buildGroupsEntity(final String id, final String name){
        return new GroupsEntity(id, name+"_"+id, buildUsersEntity());
    }

    private Set<UsersEntity> buildUsersEntity() {
        Set<UsersEntity> usersEntitySet = new HashSet<>();
        usersEntitySet.add(new UsersEntity("U10100", "Test User_U10100", null));
        usersEntitySet.add(new UsersEntity("U10101", "Test User_U10101", null));
        return usersEntitySet;
    }

    private UsersEntity buildUsersEntity(final String id, final String name){
        return new UsersEntity(id, name+"_"+id);
    }

    private GroupsEntity saveGroupsEntity(final GroupsEntity record) {
        //return groupsRepository.save(record);
        return groupsRepository.saveAndFlush(record);
    }

    private UsersEntity saveUsersEntity(final UsersEntity record) {
        //return usersRepository.save(record);
        return usersRepository.saveAndFlush(record);
    }

    private void printGroupsEntity(final GroupsEntity entity) {
        LOGGER.info("*** GroupsUsersManyToManyTesting: LISTING GROUPS ENTITY ***");
        LOGGER.info("GroupId: {}, GroupName: {}", entity.getGroupId(), entity.getGroupName());
        LOGGER.info("");
        if (!CollectionUtils.isEmpty(entity.getUsersCollection())) {
            for (UsersEntity user : entity.getUsersCollection()) {
                printUsersEntity(user);
            }
        }
        LOGGER.info("");
    }

    private void printUsersEntity(final UsersEntity entity) {
        LOGGER.info("   *** GroupsUsersManyToManyTesting: LISTING USERS ENTITY ***");
        LOGGER.info("   UserId: {}, UserName: {}", entity.getUserId(), entity.getUserName());
        LOGGER.info("");
    }

}