package com.valrock.newsline.util;

import com.valrock.newsline.model.News;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Валерий on 16.03.2017.
 */
public class NewsUtil {
    public static void main(String[] args) {
        List<News> newsList = Arrays.asList(
                new News("GameDesc", LocalDateTime.of(2017, Month.MARCH, 10, 10, 0), "alsfjasdhgjasdlfjcvzxcvkjaksdjlf", "c:/imagesForNews/image1.jpg"),
                new News("GameDesc2", LocalDateTime.of(2017, Month.MARCH, 11, 11, 0), "fdgsdfgafalsfjasdhgjasdlfjcvzxcvkjaksdjlf", "c:/imagesForNews/image2.jpg"),
                new News("GameDesc3", LocalDateTime.of(2017, Month.MARCH, 12, 12, 0), "weqersdfghalsfjasdhgjasdlfjcvzxcvkjaksdjlf", "c:/imagesForNews/image3.jpg")
        );
        List<News> filtered = getFiltered(newsList, LocalTime.of(11, 30), LocalTime.of(12, 0));

        filtered.forEach(System.out::println);
    }

    private static List<News> getFiltered(List<News> newsList, LocalTime startTime, LocalTime endTime) {
        return newsList.stream()
                .filter(news -> DateTimeUtil.isBetween(news.getTime(), startTime, endTime))
                .collect(Collectors.toList());

    }
}
