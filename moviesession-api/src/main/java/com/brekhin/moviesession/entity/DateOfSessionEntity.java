package com.brekhin.moviesession.entity;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    public DateOfSessionEntity() {
    }

    public DateOfSessionEntity(Long dateOfSessionId, Timestamp dateOfSession,
                               Set<TimeOfSessionEntity> timeOfSession) {
        this.dateOfSessionId = dateOfSessionId;
        this.dateOfSession = dateOfSession;
        this.timeOfSession = timeOfSession;
    }

    @Id
    @Column(name = "dateOfSessionId")
    public Long getDateOfSessionId() {
        return dateOfSessionId;
    }

    @Column(name = "dateOfSessionTime")
    public Timestamp getDateOfSession() {
        return dateOfSession;
    }

    @OneToMany(mappedBy = "dateOfSession", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<TimeOfSessionEntity> getTimeOfSession() {
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

    public DateOfSessionEntity setTimeOfSession(Set<TimeOfSessionEntity> timeOfSession) {
        this.timeOfSession = timeOfSession;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o, false);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
