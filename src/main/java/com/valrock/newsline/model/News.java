package com.valrock.newsline.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Валерий on 16.03.2017.
 */
public class News extends BaseEntity{

    private final String header;

    private final LocalDateTime dateTime;

    private final String textnews;

    private final String imageURL;

    public News(String header, LocalDateTime dateTime, String textnews, String imageURL) {
        this(null, header, dateTime, textnews, imageURL);
    }

    public News(Integer id, String header, LocalDateTime dateTime, String textnews, String imageURL){
        super(id);
        this.header = header;
        this.dateTime = dateTime;
        this.textnews = textnews;
        this.imageURL = imageURL;
    }

    public String getHeader() {
        return header;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTextnews() {
        return textnews;
    }

    public String getImageURL() {
        return imageURL;
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
                ", header='" + header + '\'' +
                ", dateTime=" + dateTime +
                ", textnews='" + textnews + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
