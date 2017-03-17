package com.valrock.newsline.repository;

import com.valrock.newsline.model.News;

import java.util.Collection;

/**
 * Created by Валерий on 17.03.2017.
 */
public interface NewsRepository {
    News save(News news);

    void delete(int id);

    News get(int id);

    Collection<News> getAll();
}
