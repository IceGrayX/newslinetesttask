package com.valrock.newsline.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Валерий on 16.03.2017.
 */
public class News extends BaseEntity{

    private String newsHeader;

    private LocalDateTime dateTime;

    private String textnews;

    private String imageName;

    public News(){}

    public News(String newsHeader, LocalDateTime dateTime, String textnews, String imageName) {
        this(null, newsHeader, dateTime, textnews, imageName);
    }

    public News(Integer id, String newsHeader, LocalDateTime dateTime, String textnews, String imageName){
        super(id);
        this.newsHeader = newsHeader;
        this.dateTime = dateTime;
        this.textnews = textnews;
        this.imageName = imageName;
    }

    public String getNewsHeader() {
        return newsHeader;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTextnews() {
        return textnews;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setNewsHeader(String newsHeader) {
        this.newsHeader = newsHeader;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setTextnews(String textnews) {
        this.textnews = textnews;
    }

    public LocalDate getDate(){
        return dateTime.toLocalDate();
    }

    public LocalTime getTime(){
        return dateTime.toLocalTime();
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", newsHeader='" + newsHeader + '\'' +
                ", dateTime=" + dateTime +
                ", textnews='" + textnews + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
