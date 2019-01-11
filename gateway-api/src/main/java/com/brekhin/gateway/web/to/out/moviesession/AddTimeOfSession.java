package com.brekhin.gateway.web.to.out.moviesession;

import com.fasterxml.jackson.annotation.JsonGetter;

public class AddTimeOfSession {
    private Long timeOfSessionId;

    public AddTimeOfSession(Long timeOfSessionId) {
        this.timeOfSessionId = timeOfSessionId;
    }

    @JsonGetter("timeOfSessionId")
    public Long getTimeOfSessionId() {
        return timeOfSessionId;
    }
}
