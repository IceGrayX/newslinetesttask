package com.valrock.newsline.util.exception;

/**
 * Created by Валерий on 17.03.2017.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message){
        super(message);
    }
}
