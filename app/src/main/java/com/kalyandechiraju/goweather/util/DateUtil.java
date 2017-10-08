package com.kalyandechiraju.goweather.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by kalyandechiraju on 08/10/17.
 */

public class DateUtil {
    private static final String TAG = DateUtil.class.getName();

    public static Date parseDate(String dateInString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormat.parse(dateInString);
    }
}
