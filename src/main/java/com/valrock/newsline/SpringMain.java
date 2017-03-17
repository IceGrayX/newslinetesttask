package com.valrock.newsline;

import com.valrock.newsline.model.Role;
import com.valrock.newsline.model.User;
import com.valrock.newsline.repository.UserRepository;
import com.valrock.newsline.service.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by Валерий on 17.03.2017.
 */
public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

        UserRepository userRepository = appCtx.getBean(UserRepository.class);
        userRepository.getAll();

        UserService userService = appCtx.getBean(UserService.class);
        userService.save(new User(1, "userName", "email", "password", Role.ROLE_ADMIN));

        appCtx.close();
    }
}
