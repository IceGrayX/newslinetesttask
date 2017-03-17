package com.valrock.newsline.util;

import com.valrock.newsline.model.BaseEntity;
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

    public static void checkNew(BaseEntity entity){
        if (!entity.isNew()){
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void checkIdConsistent(BaseEntity entity, int id){
        if (entity.isNew()){
            entity.setId(id);
        } else if (entity.getId() != id){
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }
}
