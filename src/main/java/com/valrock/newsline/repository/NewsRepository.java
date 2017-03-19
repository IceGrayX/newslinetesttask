package com.valrock.newsline.repository;

import com.valrock.newsline.model.News;
import org.apache.commons.fileupload.FileItem;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Валерий on 17.03.2017.
 */
public interface NewsRepository {
    News save(News news, int userId, String path, FileItem item);

    boolean delete(int id, int userId, String path);

    News get(int id, int userId);

    Collection<News> getAll(int userId);

    Collection<News> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
