package com.kalyandechiraju.goweather.util;

import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by kalyandechiraju on 08/10/17.
 */
public class DateUtilTest {
    @Test
    public void parseDate() throws Exception {
        Date date = DateUtil.parseDate("2017-10-8");
        assertNotNull(date);
    }

    @Test
    public void shouldReturnValidDay() throws Exception {
        Date date = DateUtil.parseDate("2017-10-8");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        assertEquals(8, calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void shouldReturnValidMonth() throws Exception {
        Date date = DateUtil.parseDate("2017-10-8");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        assertEquals(9, calendar.get(Calendar.MONTH));
    }

    @Test
    public void shouldReturnValidYear() throws Exception {
        Date date = DateUtil.parseDate("2017-10-8");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        assertEquals(2017, calendar.get(Calendar.YEAR));
    }

    @Test
    public void shouldFailForInvalidDate() {
        try {
            DateUtil.parseDate("2017-10-80");
            fail("No exception thrown");
        } catch (ParseException e) {
            assertNotNull(e);
        }
    }


}