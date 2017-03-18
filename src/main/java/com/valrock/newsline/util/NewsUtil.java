package com.valrock.newsline.util;

import com.valrock.newsline.model.News;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Валерий on 16.03.2017.
 */
public class NewsUtil {
    public static final List<News> NEWS_LIST = Arrays.asList(
            new News("GameDesc", LocalDateTime.of(2017, Month.MARCH, 10, 10, 0), "alsfjasdhgjasdlfjcvzxcvkjaksdjlf", "image1.jpg"),
            new News("GameDesc2", LocalDateTime.of(2017, Month.MARCH, 11, 11, 0), "fdgsdfgafalsfjasdhgjasdlfjcvzxcvkjaksdjlf", "image2.jpg"),
            new News("GameDesc3", LocalDateTime.of(2017, Month.MARCH, 12, 12, 0), "weqersdfghalsfjasdhgjasdlfjcvzxcvkjaksdjlf", "image3.jpg")
    );

    public static void main(String[] args) {
        List<News> newsList = getFiltered(NEWS_LIST, LocalTime.of(8, 0), LocalTime.of(11, 0));
        newsList.forEach(System.out::println);
    }

    public static List<News> getFiltered(Collection<News> newsList, LocalTime startTime, LocalTime endTime) {
        return newsList.stream()
                .filter(news -> DateTimeUtil.isBetween(news.getTime(), startTime, endTime))
                .collect(Collectors.toList());

    }


}
