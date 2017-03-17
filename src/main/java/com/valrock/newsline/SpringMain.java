package com.valrock.newsline;

import com.valrock.newsline.model.News;
import com.valrock.newsline.model.Role;
import com.valrock.newsline.model.User;
import com.valrock.newsline.web.news.NewsRestController;
import com.valrock.newsline.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Валерий on 17.03.2017.
 */
public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")){
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));
            System.out.println();

            NewsRestController newsController = appCtx.getBean(NewsRestController.class);
            Collection<News> filteredNews =
                    newsController.getBetween(
                            LocalDate.of(2017, Month.MARCH, 10), LocalTime.of(10, 0),
                            LocalDate.of(2017, Month.MARCH, 11), LocalTime.of(11, 0));
            filteredNews.forEach(System.out::println);
        }
    }
}
