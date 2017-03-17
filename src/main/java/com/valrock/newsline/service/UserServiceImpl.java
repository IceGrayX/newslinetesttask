package com.valrock.newsline.service;

import com.valrock.newsline.model.User;
import com.valrock.newsline.repository.UserRepository;
import com.valrock.newsline.util.exception.NotFoundException;

import java.util.List;

import static com.valrock.newsline.util.ValidationUtil.checkNotFound;
import static com.valrock.newsline.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Валерий on 17.03.2017.
 */
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }
}
