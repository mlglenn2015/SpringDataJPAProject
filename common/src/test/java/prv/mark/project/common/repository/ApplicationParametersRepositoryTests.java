package prv.mark.project.common.repository;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import prv.mark.project.common.entity.ApplicationParametersEntity;
import prv.mark.project.common.util.StringUtils;
import prv.mark.project.testutils.junit.AbstractAppTransactionalTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * JUnit tests for the {@link ApplicationParametersRepository}.
 *
 * @author mlglenn
 */
public class ApplicationParametersRepositoryTests extends AbstractAppTransactionalTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationParametersRepositoryTests.class);

    @Autowired
    private ApplicationParametersRepository applicationParametersRepository;

    @Before
    public void setUp() {
        assertNotNull(applicationParametersRepository);
    }

    @Test
    public void defaultTest() {
        LOGGER.debug("ApplicationParametersRepositoryTests.defaultTest()");
    }

    @Test
    public void testFindActiveKey() {
        ApplicationParametersEntity applicationParametersEntity
                = applicationParametersRepository.findByKey("parm.request.successful");
        assertTrue(StringUtils.isNotEmpty(applicationParametersEntity.getProperty()));
        assertEquals(applicationParametersEntity.getProperty(), "Request Successful");
    }

    @Test
    public void testFindByActiveInvalidKey() {
        assertNull(applicationParametersRepository.findByKey("TEST"));
    }
}
