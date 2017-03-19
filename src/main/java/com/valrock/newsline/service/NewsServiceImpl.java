package com.valrock.newsline.service;

import com.valrock.newsline.model.News;
import com.valrock.newsline.repository.NewsRepository;
import com.valrock.newsline.util.exception.NotFoundException;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime must not be null");
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public Collection<News> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public News update(News news, int userId, String path, FileItem item) throws NotFoundException {
        Assert.notNull(news, "news must not be null");
        return checkNotFoundWithId(repository.save(news, userId, path, item), news.getId());
    }

    @Override
    public News save(News news, int userId, String path, FileItem item) {
        Assert.notNull(news, "news must not be null");
        return repository.save(news, userId, path, item);
    }
}
