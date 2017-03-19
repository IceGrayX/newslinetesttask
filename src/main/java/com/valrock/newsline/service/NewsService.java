package com.valrock.newsline.service;

import com.valrock.newsline.model.News;
import com.valrock.newsline.util.exception.NotFoundException;
import org.apache.commons.fileupload.FileItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * Created by Валерий on 17.03.2017.
 */
public interface NewsService {
    News get(int id, int userId) throws NotFoundException;

    void delete(int id, int userId, String path) throws NotFoundException;

    default Collection<News> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId){
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }

    Collection<News> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    Collection<News> getAll(int userId);

    News update(News news, int userId, String path, FileItem item) throws NotFoundException;

    News save(News news, int userId, String path, FileItem item);
}
