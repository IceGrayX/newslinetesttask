package com.valrock.newsline.web.user;

import com.valrock.newsline.model.User;
import com.valrock.newsline.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Валерий on 17.03.2017.
 */
public abstract class AbstractUserController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    private UserService userService;

    public List<User> getAll(){
        LOG.info("getAll");
        return userService.getAll();
    }

    public User get(int id){
        LOG.info("get " + id);
        return userService.get(id);
    }

    public User create(User user){
        user.setId(null);
        LOG.info("create " + user);
        return userService.save(user);
    }

    public void delete(int id){
        LOG.info("delete " + id);
        userService.delete(id);
    }

    public void update(User user, int id){
        user.setId(id);
        LOG.info("update " + user);
        userService.update(user);
    }

    public User getByMail(String email){
        LOG.info("getByEmail " + email);
        return userService.getByEmail(email);
    }
}
