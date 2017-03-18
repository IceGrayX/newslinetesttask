package com.valrock.newsline.service;

import com.valrock.newsline.model.News;
import com.valrock.newsline.repository.NewsRepository;
import com.valrock.newsline.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

import static com.valrock.newsline.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Валерий on 17.03.2017.
 */
@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    private NewsRepository repository;

    @Override
    public News get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId, String path) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId, path), id);
    }

    @Override
    public Collection<News> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public Collection<News> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public News update(News news, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.save(news, userId), news.getId());
    }

    @Override
    public News save(News news, int userId) {
        return repository.save(news, userId);
    }
}
