package com.brekhin.moviesession.entity;


import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "time_of_session", schema = "moviesession_api")
public class TimeOfSessionEntity {

    @Id
    @Column(name = "timeOfSessionId")
    private Long timeOfSessionId;

    @Column(name = "timeOfSession")
    private Timestamp timeOfSessionDate;

    private Long movieId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dateOfSessionId")
    private DateOfSessionEntity dateOfSession;

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

    public Timestamp getTimeOfSessionDate() {
        return timeOfSessionDate;
    }
}
