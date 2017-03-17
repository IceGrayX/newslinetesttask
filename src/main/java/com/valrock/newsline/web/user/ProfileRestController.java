package com.valrock.newsline.web.user;

import com.valrock.newsline.AuthorizedUser;
import com.valrock.newsline.model.User;

/**
 * Created by Валерий on 17.03.2017.
 */
public class ProfileRestController extends AbstractUserController{

    public User get(){
        return super.get(AuthorizedUser.id());
    }

    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    public void update(User user) {
        super.update(user, AuthorizedUser.id());
    }
}
