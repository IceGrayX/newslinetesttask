package com.valrock.newsline.util;

import com.valrock.newsline.util.exception.NotFoundException;

/**
 * Created by Валерий on 17.03.2017.
 */
public class ValidationUtil {
    public static void checkNotFoundWithId(boolean found, int id){
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id){
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg){
        checkNotFound(object != null, msg);
        return object;
    }

    private static void checkNotFound(boolean found, String msg) {
        if (!found) throw new NotFoundException("Not found entity with " + msg);
    }
}
