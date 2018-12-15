package com.brekhin.gateway.web.to.out.moviesession;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Set;

public class AddDateOfSessionResponse {

    private final Long dateOfSessionId;


    public AddDateOfSessionResponse(Long dateOfSessionId) {
        this.dateOfSessionId = dateOfSessionId;
    }

    @JsonGetter("dateOfSessionId")
    public Long getDateOfSessionId() {
        return dateOfSessionId;
    }
}
