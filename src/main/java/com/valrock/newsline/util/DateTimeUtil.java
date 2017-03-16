package com.valrock.newsline.util;

import java.time.LocalTime;

/**
 * Created by Валерий on 16.03.2017.
 */
public class DateTimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime){
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
}
