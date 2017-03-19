package com.valrock.newsline.web.user;

import com.valrock.newsline.model.User;
import com.valrock.newsline.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.valrock.newsline.util.ValidationUtil.checkIdConsistent;
import static com.valrock.newsline.util.ValidationUtil.checkNew;

/**
 * Created by Валерий on 17.03.2017.
 */
public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    public List<User> getAll(){
        log.info("getAll");
        return userService.getAll();
    }

    public User get(int id){
        log.info("get " + id);
        return userService.get(id);
    }

    public User create(User user){
        checkNew(user);
        log.info("create " + user);
        return userService.save(user);
    }

    public void delete(int id){
        log.info("delete " + id);
        userService.delete(id);
    }

    public void update(User user, int id){
        checkIdConsistent(user, id);
        log.info("update " + user);
        userService.update(user);
    }

    public User getByMail(String email){
        log.info("getByEmail " + email);
        return userService.getByEmail(email);
    }
}
