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

    private String imageName;

    public News(String header, LocalDateTime dateTime, String textnews, String imageName) {
        this(null, header, dateTime, textnews, imageName);
    }

    public News(Integer id, String header, LocalDateTime dateTime, String textnews, String imageName){
        super(id);
        this.header = header;
        this.dateTime = dateTime;
        this.textnews = textnews;
        this.imageName = imageName;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
