package com.brekhin.moviesession.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "time_of_session", schema = "time_of_session")
public class TimeOfSessionEntity {

    private Long timeOfSessionId;
    private Timestamp timeOfSessionDate;
    private Long movieId;
    private int price;
    private Long hallId;

    @Id
    @Column(name = "timeOfSessionId")
    public Long getTimeOfSessionId() {
        return timeOfSessionId;
    }

    public TimeOfSessionEntity setTimeOfSessionId(Long timeOfSessionId) {
        this.timeOfSessionId = timeOfSessionId;
        return this;
    }

    @Column(name = "timeOfSession")
    public Timestamp getTimeOfSessionDate() {
        return timeOfSessionDate;
    }

    public TimeOfSessionEntity setTimeOfSessionDate(Timestamp timeOfSessionDate) {
        this.timeOfSessionDate = timeOfSessionDate;
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
