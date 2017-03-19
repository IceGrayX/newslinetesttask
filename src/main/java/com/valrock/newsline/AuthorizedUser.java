package com.valrock.newsline;

import com.valrock.newsline.model.BaseEntity;

/**
 * Created by Валерий on 17.03.2017.
 */
public class AuthorizedUser {
    public static int id = BaseEntity.START_SEQ;

    public static int id(){
        return id;
    }

    public static void setId(int id){
        AuthorizedUser.id = id;
    }

}
