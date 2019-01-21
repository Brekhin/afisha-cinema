package com.brekhin.moviesession.entity;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "time_of_session", schema = "time_of_session")
public class TimeOfSessionEntity {

    private Long timeOfSessionId;
    private String timeOfSession;
    private Long movieId;
    private int price;
    private Long hallId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "timeOfSessionId")
    public Long getTimeOfSessionId() {
        return timeOfSessionId;
    }

    public TimeOfSessionEntity setTimeOfSessionId(Long timeOfSessionId) {
        this.timeOfSessionId = timeOfSessionId;
        return this;
    }

    @Column(name = "timeOfSession")
    public String getTimeOfSession() {
        return timeOfSession;
    }

    public TimeOfSessionEntity setTimeOfSession(String timeOfSession) {
        this.timeOfSession = timeOfSession;
        return this;
    }

    @Column(name = "movie_id")
    public Long getMovieId() {
        return movieId;
    }

    public TimeOfSessionEntity setMovieId(Long movieId) {
        this.movieId = movieId;
        return this;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public TimeOfSessionEntity setPrice(int price) {
        this.price = price;
        return this;
    }

    @Column(name = "hallId")
    public Long getHallId() {
        return hallId;
    }

    public TimeOfSessionEntity setHallId(Long hallId) {
        this.hallId = hallId;
        return this;
    }
}
