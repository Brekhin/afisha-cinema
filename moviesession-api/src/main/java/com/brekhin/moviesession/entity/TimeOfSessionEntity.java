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

    @ElementCollection
    @CollectionTable(
        name = "movies",
        joinColumns = @JoinColumn(name = "time_of_session_id", referencedColumnName = "time_of_session_id")
    )
    @Column(name = "movieId")
    private List<Long> movieIds;

    @ManyToOne
    @JoinColumn(name = "date_of_sessionid", nullable = false)
    private DateOfSessionEntity dateOfSession;

    public TimeOfSessionEntity(Long timeOfSessionId, Timestamp timeOfSessionDate,
                               List<Long> movieIds, DateOfSessionEntity dateOfSession) {
        this.timeOfSessionId = timeOfSessionId;
        this.timeOfSessionDate = timeOfSessionDate;
        this.movieIds = movieIds;
        this.dateOfSession = dateOfSession;
    }

    public TimeOfSessionEntity() {
    }

    public Long getTimeOfSessionId() {
        return timeOfSessionId;
    }

    private TimeOfSessionEntity setTimeOfSessionId(Long timeOfSessionId) {
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


    public List<Long> getMovieIds() {
        return movieIds;
    }

    public TimeOfSessionEntity setMovieIds(Long movieId) {
        if (!movieIds.isEmpty()) {
            movieIds.add(movieId);
        } else {
            movieIds = new ArrayList<>();
        }
        return this;
    }

    public void setMovieIds(List<Long> movieIds) {
        this.movieIds = movieIds;
    }

    public DateOfSessionEntity getDateOfSession() {
        return dateOfSession;
    }

    public TimeOfSessionEntity setDateOfSession(DateOfSessionEntity dateOfSession) {
        this.dateOfSession = dateOfSession;
        return this;
    }


}
