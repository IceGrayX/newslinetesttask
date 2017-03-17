package com.valrock.newsline.web.news;

import com.valrock.newsline.AuthorizedUser;
import com.valrock.newsline.model.News;
import com.valrock.newsline.service.NewsService;
import com.valrock.newsline.util.DateTimeUtil;
import com.valrock.newsline.util.NewsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static com.valrock.newsline.util.ValidationUtil.checkIdConsistent;
import static com.valrock.newsline.util.ValidationUtil.checkNew;

/**
 * Created by Валерий on 17.03.2017.
 */
@Controller
public class NewsRestController {
    private static final Logger LOG = LoggerFactory.getLogger(NewsRestController.class);

    @Autowired
    private NewsService newsService;

    public News get(int id){
        int userId = AuthorizedUser.id();
        LOG.info("get news {} for User {}", id, userId);
        return newsService.get(id, userId);
    }

    public void delete(int id){
        int userId = AuthorizedUser.id();
        LOG.info("delete news {} for User {}", id, userId);
        newsService.delete(id, userId);
    }

    public Collection<News> getAll(){
        int userId = AuthorizedUser.id();
        LOG.info("getAll for User {}", userId);
        return newsService.getAll(userId);
    }

    public News create(News news){
        checkNew(news);
        int userId = AuthorizedUser.id();
        LOG.info("create {} for User {}", news, userId);
        return newsService.save(news, userId);
    }

    public void update(News news, int id){
        checkIdConsistent(news, id);
        int userId = AuthorizedUser.id();
        LOG.info("update {} for User {}", news, userId);
        newsService.update(news, userId);
    }

    public Collection<News> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime){
        int userId = AuthorizedUser.id();
        LOG.info("getBetween dates {} - {} for time {} - {} for User {}", startDate, endDate, startTime, endTime, userId);

        return NewsUtil.getFiltered(newsService.getBetweenDates(
                startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX
        );
    }
}
