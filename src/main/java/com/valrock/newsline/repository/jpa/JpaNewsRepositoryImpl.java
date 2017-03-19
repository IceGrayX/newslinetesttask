package com.valrock.newsline.repository.jpa;

import com.valrock.newsline.model.News;
import com.valrock.newsline.model.User;
import com.valrock.newsline.repository.NewsRepository;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;

import static com.valrock.newsline.util.NewsUtil.deleteFile;
import static com.valrock.newsline.util.NewsUtil.saveFile;

/**
 * Created by Валерий on 19.03.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaNewsRepositoryImpl implements NewsRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public News save(News news, int userId, String path, FileItem item) {
        if (!news.isNew() && get(news.getId(), userId) == null){
            return null;
        }
        news.setUser(em.getReference(User.class, userId));
        if (news.isNew()){
            news.setImageName(saveFile(path, item));
            em.persist(news);
            return news;
        } else {
            deleteFile(path + get(news.getId(), userId).getImageName());
            news.setImageName(saveFile(path, item));
            return em.merge(news);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId, String path) {
        deleteFile(path + get(id, userId).getImageName());
        return em.createNamedQuery(News.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public News get(int id, int userId) {
        News news = em.find(News.class, id);
        return news != null && news.getUser().getId() == userId ? news : null;
    }

    @Override
    public Collection<News> getAll(int userId) {
        return em.createNamedQuery(News.ALL_SORTED, News.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Collection<News> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(News.GET_BETWEEN, News.class)
                .setParameter("userId", userId)
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
    }
}
