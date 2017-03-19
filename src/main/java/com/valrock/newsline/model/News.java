package com.valrock.newsline.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by Валерий on 16.03.2017.
 */
@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = News.ALL_SORTED,
        query = "SELECT n FROM News n WHERE n.user.id=:userId ORDER BY n.dateTime DESC"),
        @NamedQuery(name = News.DELETE,
        query = "DELETE FROM News n WHERE n.id=:id AND n.user.id=:userId"),
        @NamedQuery(name = News.GET_BETWEEN,
        query = "SELECT n FROM News n WHERE n.user.id=:userId AND n.dateTime BETWEEN :startDateTime AND :endDateTime ORDER BY n.dateTime DESC")
})
@Entity
@Table(name = "news", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "dateTime"}, name = "news_unique_user_datetime_ind")
})
public class News extends BaseEntity{

    public static final String ALL_SORTED = "News.getAll";
    public static final String DELETE = "News.delete";
    public static final String GET_BETWEEN = "News.getBetween";

    @Column(name = "newsHeader", nullable = false)
    @NotBlank
    private String newsHeader;

    @Column(name = "dateTime", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "textnews", nullable = false)
    @NotBlank
    private String textnews;

    @Column(name = "imageName", nullable = false)
    @NotBlank
    private String imageName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
