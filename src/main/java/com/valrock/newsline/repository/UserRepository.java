package com.valrock.newsline.repository;

import com.valrock.newsline.model.User;

import java.util.List;

/**
 * Created by Валерий on 17.03.2017.
 */
public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(int id);

    //null if not found
    User get(int id);

    //null if not found
    User getByEmail(String email);

    List<User> getAll();
}
