package com.memoquest.service.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by franck on 30/10/2014.
 */
public class MyDateUtils {

    public Date setDateFromString(String date) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy");
        sf.setLenient(true);
        return sf.parse(date);
    }
}
