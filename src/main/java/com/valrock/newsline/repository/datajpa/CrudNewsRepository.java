package com.valrock.newsline.repository.datajpa;

import com.valrock.newsline.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Валерий on 19.03.2017.
 */
public interface CrudNewsRepository extends JpaRepository<News, Integer> {
}
