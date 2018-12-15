package com.brekhin.gateway.web.to.in.moviesession;

import com.brekhin.gateway.web.to.out.moviesession.AddTimeOfSessionResponse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;
import java.util.Set;

public class AddDateOfSessionRequest {

    private final Long dateOfSessionId;
    private final Timestamp dateOfSession;
    private final Set<AddTimeOfSessionRequest> timeOfSession;

    public AddDateOfSessionRequest(@JsonProperty("dateOfSessionId") Long dateOfSessionId,
                                   @JsonProperty("dateOfSession") Timestamp dateOfSession,
                                   @JsonProperty("timeOfSession") Set<AddTimeOfSessionRequest> timeOfSession) {
        this.dateOfSessionId = dateOfSessionId;
        this.dateOfSession = dateOfSession;
        this.timeOfSession = timeOfSession;
    }

    public Long getDateOfSessionId() {
        return dateOfSessionId;
    }

    public Timestamp getDateOfSession() {
        return dateOfSession;
    }

    public Set<AddTimeOfSessionRequest> getTimeOfSession() {
        return timeOfSession;
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
