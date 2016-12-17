package prv.mark.project.common.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import prv.mark.project.testutils.config.TestUtilConfig;
import prv.mark.project.testutils.junit.AbstractAppTransactionalTest;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Junit tests for the {@link NumberUtils} class.
 *
 * Created by mlglenn on 11/15/2016.
 */
@ContextConfiguration(classes = {TestUtilConfig.class})
public class NumberUtilsTests extends AbstractAppTransactionalTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NumberUtilsTests.class);


    @Override
    @Before
    public void setUp() {
        LOGGER.debug("NumberUtilsTests.setUp(): -----> CREATE <-----");
    }

    @Override
    @After
    public void tearDown() {
        LOGGER.debug("NumberUtilsTests.tearDown(): -----> DESTROY <-----");
    }

    @Test
    public void defaultTest() {
        LOGGER.debug("NumberUtilsTests.defaultTest()");
    }

    @Test
    public void testBigDecimalWithDouble() {
        Double doubleVal = new Double(123456789);
        BigDecimal bigDecimal = NumberUtils.toBigDecimal(doubleVal);
        assertNotNull(bigDecimal);
        LOGGER.debug("Value is {}", bigDecimal);
    }

    @Test
    public void testBigDecimalWithFloat() {
        Float floatVal = new Float(123456789.00);
        BigDecimal bigDecimal = NumberUtils.toBigDecimal(floatVal);
        assertNotNull(bigDecimal);
        LOGGER.debug("Value is {}", bigDecimal);
    }

    @Test
    public void testBigDecimalWithLong() {
        Long longVal = new Long(123456789);
        BigDecimal bigDecimal = NumberUtils.toBigDecimal(longVal);
        assertNotNull(bigDecimal);
        LOGGER.debug("Value is {}", bigDecimal);
    }

    @Test
    public void testBigDecimalWithInteger() {
        Integer intVal = new Integer(123456789);
        BigDecimal bigDecimal = NumberUtils.toBigDecimal(intVal);
        assertNotNull(bigDecimal);
        LOGGER.debug("Value is {}", bigDecimal);
    }

    @Test
    public void testToInt() {
        String val = "123";
        assertTrue(StringUtils.isNumeric(val));
        BigDecimal bigDecimal = NumberUtils.toBigDecimal(NumberUtils.toInt(val));
        assertNotNull(bigDecimal);
        LOGGER.debug("Value is {}", bigDecimal);
    }

    @Test
    public void testToLong() {
        String val = "456";
        assertTrue(StringUtils.isNumeric(val));
        BigDecimal bigDecimal = NumberUtils.toBigDecimal(NumberUtils.toLong(val));
        assertNotNull(bigDecimal);
        LOGGER.debug("Value is {}", bigDecimal);
    }

    @Test
    public void testToLongFromInteger() {
        Integer val = new Integer(456);
        Long longVal = 456L;
        Long longValue = NumberUtils.toLong(val);
        assertEquals(longValue, longVal);
        LOGGER.debug("Value is {}", longValue);
    }

    @Test
    public void testLong() {
        long val = 456;
        assertTrue(NumberUtils.isPositiveNumber(val));
    }
}
