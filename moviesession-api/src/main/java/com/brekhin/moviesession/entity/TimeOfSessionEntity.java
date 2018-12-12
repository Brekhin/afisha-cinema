package com.brekhin.moviesession.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "time_of_session", schema = "moviesession_api")
public class TimeOfSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_of_session_id")
    private Long timeOfSessionId;

    @Column(name = "time_of_session")
    private Timestamp timeOfSessionDate;

    private Long movieId;

    @ManyToOne
    @JoinColumn(name = "date_of_sessionid", nullable = false)
    private DateOfSessionEntity dateOfSession;

    public TimeOfSessionEntity(Long timeOfSessionId, Timestamp timeOfSessionDate,
                               Long movieId, DateOfSessionEntity dateOfSession) {
        this.timeOfSessionId = timeOfSessionId;
        this.timeOfSessionDate = timeOfSessionDate;
        this.movieId = movieId;
        this.dateOfSession = dateOfSession;
    }

    public TimeOfSessionEntity() {
    }

    public Long getTimeOfSessionId() {
        return timeOfSessionId;
    }

    public TimeOfSessionEntity setTimeOfSessionId(Long timeOfSessionId) {
        this.timeOfSessionId = timeOfSessionId;
        return this;
    }

    public Timestamp getTimeOfSession() {
        return timeOfSessionDate;
    }

    public TimeOfSessionEntity setTimeOfSessionDate(Timestamp timeOfSessionDate) {
        this.timeOfSessionDate = timeOfSessionDate;
        return this;
    }


    public Long getMovieId() {
        return movieId;
    }

    public TimeOfSessionEntity setMovieId(Long movieId) {
        this.movieId = movieId;
        return this;
    }

    public DateOfSessionEntity getDateOfSession() {
        return dateOfSession;
    }

    public TimeOfSessionEntity setDateOfSession(DateOfSessionEntity dateOfSession) {
        this.dateOfSession = dateOfSession;
        return this;
    }


}
