package com.memoquest.service.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by franck on 30/10/2014.
 */
public class MyDateUtils {


    public static Date convertDateStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

       // dateFormat.setLenient(true);

        return dateFormat.parse(dateString);
    }
}
