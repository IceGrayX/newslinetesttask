package com.valrock.newsline.repository.jdbc;

import com.valrock.newsline.model.News;
import com.valrock.newsline.repository.NewsRepository;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import static com.valrock.newsline.util.NewsUtil.deleteFile;
import static com.valrock.newsline.util.NewsUtil.saveFile;

/**
 * Created by Валерий on 19.03.2017.
 */
@Repository
public class JdbcNewsRepositoryImpl implements NewsRepository {

    private static final RowMapper<News> ROW_MAPPER = BeanPropertyRowMapper.newInstance(News.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertNews;

    @Autowired
    public JdbcNewsRepositoryImpl(DataSource dataSource) {
        this.insertNews = new SimpleJdbcInsert(dataSource)
                .withTableName("news")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public News save(News news, int userId, String path, FileItem item) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", news.getId())
                .addValue("newsHeader", news.getNewsHeader())
                .addValue("dateTime", news.getDateTime())
                .addValue("textnews", news.getTextnews());

        if (news.isNew()) {
            news.setImageName(saveFile(path, item));
            map.addValue("imageName", news.getImageName())
                    .addValue("userId", userId);
            Number newId = insertNews.executeAndReturnKey(map);
            news.setId(newId.intValue());
        } /*else if (get(news.getId(), userId) == null){
            return null;
        } */else {
            deleteFile(path + get(news.getId(), userId).getImageName());
            news.setImageName(saveFile(path, item));
            map.addValue("imageName", news.getImageName())
                    .addValue("userId", userId);
            namedParameterJdbcTemplate.update("" +
                    "UPDATE news " +
                    " SET newsHeader=:newsHeader, dateTime=:dateTime, textnews=:textnews, imageName=:imageName" +
                    " WHERE id=:id AND userId=:userId", map);
        }
        return news;
    }

    @Override
    public boolean delete(int id, int userId, String path) {
        deleteFile(path + get(id, userId).getImageName());
        return jdbcTemplate.update("DELETE FROM news WHERE id=? AND userId=?", id, userId) != 0;
    }

    @Override
    public News get(int id, int userId) {
        List<News> newsList = jdbcTemplate.query(
                "SELECT * FROM news WHERE id = ? AND userid = ?", ROW_MAPPER, id, userId
        );
        return DataAccessUtils.singleResult(newsList);
    }

    @Override
    public Collection<News> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM news WHERE userid = ? ORDER BY datetime DESC", ROW_MAPPER, userId
        );
    }

    @Override
    public Collection<News> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM news WHERE userid = ? AND datetime BETWEEN ? AND ? ORDER BY datetime DESC",
                ROW_MAPPER, userId, startDateTime, endDateTime
        );
    }
}
