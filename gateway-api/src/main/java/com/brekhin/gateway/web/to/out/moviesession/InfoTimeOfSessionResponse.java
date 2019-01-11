package com.brekhin.gateway.web.to.out.moviesession;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class InfoTimeOfSessionResponse {

    private final Long timeOfSessionId;
    private final Timestamp timeOfSessionDate;
    private final int price;

    public InfoTimeOfSessionResponse(
            Long timeOfSessionId,
            Timestamp timeOfSessionDate,
            int price) {
        this.timeOfSessionId = timeOfSessionId;
        this.timeOfSessionDate = timeOfSessionDate;
        this.price = price;
    }

    @JsonGetter("timeOfSessionId")
    public Long getTimeOfSessionId() {
        return timeOfSessionId;
    }

    @JsonGetter("timeOfSessionDate")
    public Timestamp getTimeOfSessionDate() {
        return timeOfSessionDate;
    }

    @JsonGetter("price")
    public int getPrice() {
        return price;
    }
}
