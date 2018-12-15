package com.brekhin.gateway.web.to.in.moviesession;

import com.brekhin.gateway.web.to.out.moviesession.AddDateOfSessionResponse;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class AddTimeOfSessionRequest {

    private final Long timeOfSessionId;
    private final Timestamp timeOfSessionDate;
    private final Long movieId;

    Logger log = LoggerFactory.getLogger(AddTimeOfSessionRequest.class);
    public AddTimeOfSessionRequest(@JsonProperty("timeOfSessionId") Long timeOfSessionId,
                                   @JsonProperty("timeOfSessionDate") Timestamp timeOfSessionDate,
                                   @JsonProperty("movieId") Long movieId) {
        this.timeOfSessionId = timeOfSessionId;
        this.timeOfSessionDate = timeOfSessionDate;
        this.movieId = movieId;
    }

    public Long getTimeOfSessionId() {
        return timeOfSessionId;
    }

    public Timestamp getTimeOfSessionDate() {
        return timeOfSessionDate;
    }

    public Long getMovieId() {
        return movieId;
    }
}
