package prv.mark.project.common.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import prv.mark.project.testutils.config.TestUtilConfig;
import prv.mark.project.testutils.junit.AbstractAppTransactionalTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Junit tests for the {@link StringUtils} class.
 *
 * @author mlglenn.
 */
@ContextConfiguration(classes = {TestUtilConfig.class})
public class StringUtilsTests extends AbstractAppTransactionalTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringUtilsTests.class);


    @Override
    @Before
    public void setUp() {
        LOGGER.debug("StringUtilsTests.setUp(): -----> CREATE <-----");
    }

    @Override
    @After
    public void tearDown() {
        LOGGER.debug("StringUtilsTests.tearDown(): -----> DESTROY <-----");
    }

    @Test
    public void defaultTest() {
        LOGGER.debug("StringUtilsTests.defaultTest()");
    }

    @Test
    public void testSafeString() {
        LOGGER.debug("StringUtilsTests.testSafeString()");
        assertNotNull(StringUtils.safeString(null));
        assertEquals(StringUtils.safeString(null), StringUtils.EMPTY);
    }

    @Test
    public void testEmpty() {
        LOGGER.debug("StringUtilsTests.testEmpty()");
        assertEquals("", StringUtils.EMPTY);
        assertEquals(new String(), StringUtils.EMPTY);
    }

    @Test
    public void testIsBlank() {
        LOGGER.debug("StringUtilsTests.testIsBlank()");
        assertTrue(StringUtils.isBlank(StringUtils.BLANK));
    }

    @Test
    public void testIsEmpty() {
        LOGGER.debug("StringUtilsTests.testIsEmpty()");
        assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    public void testIsNotEmpty() {
        LOGGER.debug("StringUtilsTests.testIsNotEmpty()");
        assertTrue(StringUtils.isNotEmpty(" f oo"));
    }

    @Test
    public void testRegEx() {
        LOGGER.debug("StringUtilsTests.testRegEx()");
        assertTrue(StringUtils.testRegEx("\\d{16}", "0036003123456789"));
    }

    @Test
    public void testIsNumeric() {
        LOGGER.debug("StringUtilsTests.testIsNumeric()");
        assertTrue(StringUtils.isNumeric("12345678"));
    }

    @Test
    public void testNegativeIsNumeric() {
        LOGGER.debug("StringUtilsTests.testNegativeIsNumeric()");
        assertFalse(StringUtils.isNumeric("X"));
    }

    @Test
    public void testZeroPadStringFromInt() {
        LOGGER.debug("StringUtilsTests.testZeroPadStringFromInt()");
        assertNotNull(StringUtils.getZeroPadStringFromInt(3, 2, 2));
        assertEquals(StringUtils.getZeroPadStringFromInt(3, 2, 2), "03");
    }

    @Test
    public void testGetZeroPadString() {
        LOGGER.debug("StringUtilsTests.testGetZeroPadString()");
        assertNotNull(StringUtils.getZeroPadString("ABC", 6, 'Z'));
        assertEquals(StringUtils.getZeroPadString("ABC", 6, 'Z'), "ZZZABC");
    }

    @Test
    public void testContainsIgnoreCase() {
        LOGGER.debug("StringUtilsTests.testContainsIgnoreCase()");
        assertTrue(StringUtils.containsIgnoreCase("I SAW A CROW , ORCA WAS I", "WAS"));
    }

    @Test
    public void testNegativeContainsIgnoreCase() {
        LOGGER.debug("StringUtilsTests.testNegativeContainsIgnoreCase()");
        assertFalse(StringUtils.containsIgnoreCase("I SAW A CROW , ORCA WAS I", "BIRD"));
    }

}
