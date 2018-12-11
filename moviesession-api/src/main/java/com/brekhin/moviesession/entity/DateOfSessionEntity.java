package com.brekhin.moviesession.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "date_of_session", schema = "moviesession_api")
public class DateOfSessionEntity {

    private Long dateOfSessionId;
    private Timestamp dateOfSession;
    private Set<TimeOfSessionEntity> timeOfSession = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_of_sessionid")
    public Long getDateOfSessionId() {
        return dateOfSessionId;
    }

    @Column(name = "date_of_session")
    public Timestamp getDateOfSession() {
        return dateOfSession;
    }

    @OneToMany(mappedBy = "dateOfSession", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Set<TimeOfSessionEntity> getTimeOfSessionEntitie() {
        return timeOfSession;
    }

    public DateOfSessionEntity setDateOfSessionId(Long dateOfSessionId) {
        this.dateOfSessionId = dateOfSessionId;
        return this;
    }

    public DateOfSessionEntity setDateOfSession(Timestamp dateOfSession) {
        this.dateOfSession = dateOfSession;
        return this;
    }

    public DateOfSessionEntity setTimeOfSession(TimeOfSessionEntity timeOfSession) {
        this.timeOfSession.add(timeOfSession);
        return this;
    }

    public DateOfSessionEntity setTimeOfSession(Set<TimeOfSessionEntity> timeOfSession) {
        this.timeOfSession = timeOfSession;
        return this;
    }


}
