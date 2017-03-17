package com.valrock.newsline.repository.mock;

import com.valrock.newsline.model.News;
import com.valrock.newsline.repository.NewsRepository;
import com.valrock.newsline.util.DateTimeUtil;
import com.valrock.newsline.util.NewsUtil;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.valrock.newsline.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;
import static com.valrock.newsline.repository.mock.InMemoryUserRepositoryImpl.USER_ID;

/**
 * Created by Валерий on 17.03.2017.
 */
@Repository
public class InMemoryNewsRepositoryImpl implements NewsRepository {
    private static final Comparator<News> NEWS_COMPARATOR = Comparator.comparing(News::getDateTime).reversed();

    private Map<Integer, Map<Integer, News>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        NewsUtil.NEWS_LIST.forEach(news -> save(news, USER_ID));

        save(new News("NewsAdmin1", LocalDateTime.of(2017, Month.MARCH, 1, 10, 0), "texttexttexttexttexttext", "imageURL1"), ADMIN_ID);
        save(new News("NewsAdmin2", LocalDateTime.of(2017, Month.MARCH, 2, 11, 0), "texttexttexttexttexttext", "imageURL2"), ADMIN_ID);
    }


    @Override
    public News save(News news, int userId) {
        Objects.requireNonNull(news);

        Map<Integer, News> newsMap = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (news.isNew()){
            news.setId(counter.incrementAndGet());
        } else if (get(news.getId(), userId) == null){
            return null;
        }
        newsMap.put(news.getId(), news);
        return news;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, News> newsMap = repository.get(userId);
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

    private Stream<News> getAllAsStream(int userId) {
        Map<Integer, News> newsMap = repository.get(userId);
        return newsMap == null ?
                Stream.empty() : newsMap.values().stream().sorted(NEWS_COMPARATOR);
    }

    @Override
    public Collection<News> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Objects.requireNonNull(startDateTime);
        Objects.requireNonNull(endDateTime);
        return getAllAsStream(userId)
                .filter(news -> DateTimeUtil.isBetween(news.getDateTime(), startDateTime, endDateTime))
                .collect(Collectors.toList());
    }
}
