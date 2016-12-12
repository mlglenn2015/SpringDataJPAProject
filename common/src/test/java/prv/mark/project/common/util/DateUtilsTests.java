package prv.mark.project.common.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import prv.mark.project.common.config.TestCommonConfig;
import prv.mark.project.testutils.junit.AbstractAppTransactionalTest;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = {TestCommonConfig.class})
public class DateUtilsTests extends AbstractAppTransactionalTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtilsTests.class);


    @Test
    public void defaultTest() {

    }

    @Test
    public void testLocalDate() {
        LOGGER.debug("testLocalDate()");
        LocalDate returnedDate = DateUtils.getLocalDate();
        assertNotNull(returnedDate);
    }

    @Test
    public void testLocalDateTime() {
        LOGGER.debug("testLocalDateTime()");
        LocalDateTime returnedDate = DateUtils.getLocalDateTime();
        assertNotNull(returnedDate);
    }

    @Test
    public void testLocalDateTimeFromDate() {
        LOGGER.debug("testLocalDateTimeFromDate()");
        Date date = new Date();
        LocalDateTime returnedDate = DateUtils.getLocalDateTimeFromDate(date);
        assertNotNull(returnedDate);
    }

    @Test
    public void testDateFromLocalDateTime() {
        LOGGER.debug("testDateFromLocalDateTime()");
        LocalDateTime localDateTime = DateUtils.getLocalDateTime();
        Date returnedDate = DateUtils.getDateFromLocalDateTime(localDateTime);
        assertNotNull(returnedDate);
    }

    @Test
    public void testToXMLGregorianCalendar() {
        LOGGER.debug("testToXMLGregorianCalendar()");
        XMLGregorianCalendar returnedXmlCal = DateUtils.getCurrentXMLGregorianCalendar();
        assertNotNull(returnedXmlCal);
    }

    @Test
    public void testXmlGregorianCalendarToDate() {
        LOGGER.debug("testXmlGregorianCalendarToDate()");
        XMLGregorianCalendar returnedXmlCal = DateUtils.getCurrentXMLGregorianCalendar();
        Date returnedDate = DateUtils.xmlGregorianCalendarToDate(returnedXmlCal);
        assertNotNull(returnedDate);
    }

    private XMLGregorianCalendar returnControlXmlCal() {
        XMLGregorianCalendar controlXmlCal = null;
        Calendar cal = Calendar.getInstance();
        assertNotNull(cal);

        try {
            DatatypeFactory dtf = DatatypeFactory.newInstance();
            controlXmlCal = dtf.newXMLGregorianCalendar();
            controlXmlCal.setYear(cal.get(Calendar.YEAR));
            controlXmlCal.setMonth(cal.get(Calendar.MONTH) + 1);
            controlXmlCal.setDay(cal.get(Calendar.DAY_OF_MONTH));
            controlXmlCal.setHour(cal.get(Calendar.HOUR_OF_DAY));
            controlXmlCal.setMinute(cal.get(Calendar.MINUTE));
            controlXmlCal.setSecond(cal.get(Calendar.SECOND));
            controlXmlCal.setMillisecond(cal.get(Calendar.MILLISECOND));
            int offsetInMinutes =
                    (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET)) / (60 * 1000);
            controlXmlCal.setTimezone(offsetInMinutes);

        } catch (IllegalArgumentException | DatatypeConfigurationException e) {
            LOGGER.error(e.getMessage());
        }
        return controlXmlCal;
    }

}
