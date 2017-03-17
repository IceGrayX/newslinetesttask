package com.valrock.newsline.repository.mock;

import com.valrock.newsline.model.News;
import com.valrock.newsline.repository.NewsRepository;
import com.valrock.newsline.util.NewsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Валерий on 17.03.2017.
 */
public class InMemoryNewsRepositoryImpl implements NewsRepository {
    private Map<Integer, News> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        NewsUtil.NEWS_LIST.forEach(this::save);
    }

    @Override
    public News save(News news) {
        if (news.isNew()){
            news.setId(counter.incrementAndGet());
        }
        repository.put(news.getId(), news);
        return news;
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public News get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<News> getAll() {
        return repository.values();
    }
}
