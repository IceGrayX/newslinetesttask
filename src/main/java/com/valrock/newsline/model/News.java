package com.valrock.newsline.model;

import java.time.LocalDateTime;

/**
 * Created by Валерий on 16.03.2017.
 */
public class News {
    private final String header;

    private final LocalDateTime dateTime;

    private final String textnews;

    private final String imageURL;

    public News(String header, LocalDateTime dateTime, String textnews, String imageURL) {
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

    @Override
    public String toString() {
        return "News{" +
                "header='" + header + '\'' +
                ", dateTime=" + dateTime +
                ", textnews='" + textnews + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
