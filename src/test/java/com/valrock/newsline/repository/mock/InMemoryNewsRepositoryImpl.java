package com.valrock.newsline.repository.mock;

import com.valrock.newsline.model.News;
import com.valrock.newsline.repository.NewsRepository;
import com.valrock.newsline.util.DateTimeUtil;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.valrock.newsline.util.NewsUtil.deleteFile;
import static com.valrock.newsline.util.NewsUtil.saveFile;

/**
 * Created by Валерий on 17.03.2017.
 */
@Repository
public class InMemoryNewsRepositoryImpl implements NewsRepository {
    private static final Comparator<News> NEWS_COMPARATOR = Comparator.comparing(News::getDateTime).reversed();

    private Map<Integer, Map<Integer, News>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public News save(News news, int userId, String path, FileItem item) {

        Map<Integer, News> newsMap = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (news.isNew()){
            news.setId(counter.incrementAndGet());
            news.setImageName(saveFile(path, item));
        } else if (get(news.getId(), userId) == null){
            return null;
        } else {
            deleteFile(path + newsMap.get(news.getId()).getImageName());
            news.setImageName(saveFile(path, item));
        }
        newsMap.put(news.getId(), news);
        return news;
    }

    @Override
    public boolean delete(int id, int userId, String path) {
        Map<Integer, News> newsMap = repository.get(userId);
        deleteFile(path + newsMap.get(id).getImageName());
        return newsMap != null && newsMap.remove(id) != null;
    }

    @Override
    public News get(int id, int userId) {
        Map<Integer, News> newsMap = repository.get(userId);
        return newsMap == null ? null : newsMap.get(id);
    }

    @Override
    public Collection<News> getAll(int userId) {
        return getAllAsStream(userId).collect(Collectors.toList());
    }

    @Override
    public Collection<News> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return getAllAsStream(userId)
                .filter(news -> DateTimeUtil.isBetween(news.getDateTime(), startDateTime, endDateTime))
                .collect(Collectors.toList());
    }

    private Stream<News> getAllAsStream(int userId) {
        Map<Integer, News> newsMap = repository.get(userId);
        return newsMap == null ?
                Stream.empty() : newsMap.values().stream().sorted(NEWS_COMPARATOR);
    }
}
