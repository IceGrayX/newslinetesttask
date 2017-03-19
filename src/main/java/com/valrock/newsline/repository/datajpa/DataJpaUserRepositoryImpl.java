package com.valrock.newsline.repository.datajpa;

import com.valrock.newsline.model.User;
import com.valrock.newsline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Валерий on 19.03.2017.
 */

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository{
    private static final Sort SORT_NAME_EMAIL = new Sort("name", "email");

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudUserRepository.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }
}
