package com.valrock.newsline.repository.datajpa;

import com.valrock.newsline.model.News;
import com.valrock.newsline.repository.NewsRepository;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Валерий on 19.03.2017.
 */
@Repository
public class DataJpaNewsRepositoryImpl implements NewsRepository{

    @Autowired
    private CrudNewsRepository crudNewsRepository;

    @Override
    public News save(News news, int userId, String path, FileItem item) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId, String path) {
        return false;
    }

    @Override
    public News get(int id, int userId) {
        return null;
    }

    @Override
    public Collection<News> getAll(int userId) {
        return null;
    }

    @Override
    public Collection<News> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return null;
    }
}
