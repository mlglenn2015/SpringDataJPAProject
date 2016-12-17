package prv.mark.project.testutils.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;
import prv.mark.project.testutils.config.TestDataConfig;

/**
 * Generic transactional unit test foundation.
 * <p> Provides database and transactional facilities used by the applciation. </p>
 * <p> NOTE: Classes deriving from this class <b><i>must provide</i></b> the
 * {@link org.springframework.test.context.ContextConfiguration} annotation specific to the
 * particular test's Spring configuration. </p>
 *
 * @author mlglenn.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class})
@ComponentScan(basePackages = "prv.mark.project")
@ActiveProfiles("test")
@DirtiesContext
@Transactional
public class AbstractAppTransactionalTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAppTransactionalTest.class);

    /**
     * Method used to bootstrap test class.
     */
    @BeforeClass
    public static void bootstrap() {
    }

    /**
     * Method called after all tests are completed.
     */
    @AfterClass
    public static void destroy() {
    }

    /**
     * Method called before every test is run.
     * <p>
     * <p>Useful for initializing variables used in every test.</p>
     */
    @Before
    public void setUp() {
    }

    /**
     * Method called after each test is run.
     */
    @After
    public void tearDown() {
    }

    @BeforeTransaction
    public void beforeTransaction() {
        LOGGER.trace("Starting transaction");
    }

    @AfterTransaction
    public void afterTransaction() {
        LOGGER.trace("End of transaction");
    }

}
