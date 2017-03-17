package com.valrock.newsline.service;

import com.valrock.newsline.model.User;
import com.valrock.newsline.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Валерий on 17.03.2017.
 */
public interface UserService {
    User save(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    List<User> getAll();

    void update(User user);
}
