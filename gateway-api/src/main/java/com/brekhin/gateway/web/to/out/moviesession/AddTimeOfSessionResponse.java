package com.brekhin.gateway.web.to.out.moviesession;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class AddTimeOfSessionResponse {

    private final Long timeOfSessionId;


    public AddTimeOfSessionResponse(Long dateOfSessionId) {
        this.timeOfSessionId = dateOfSessionId;
    }

    @JsonGetter("timeOfSessionId")
    public Long getDateOfSessionId() {
        return timeOfSessionId;
    }
}
