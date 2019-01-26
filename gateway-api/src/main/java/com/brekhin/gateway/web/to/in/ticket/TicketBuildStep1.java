package com.brekhin.gateway.web.to.in.ticket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketBuildStep1 {

    private Long sessionId;
    private String movieName;
    private String hallName;
    private String sessionTime;
    private int price;

    @JsonCreator
    public TicketBuildStep1(
            @JsonProperty("sessionId") Long sessionId,
            @JsonProperty("movieName") String movieName,
            @JsonProperty("hallName") String hallName,
            @JsonProperty("sessionTime") String sessionTime,
            @JsonProperty("price") int price) {
        this.sessionId = sessionId;
        this.movieName = movieName;
        this.hallName = hallName;
        this.sessionTime = sessionTime;
        this.price = price;
    }

    @JsonGetter("sessionId")
    public Long getSessionId() {
        return sessionId;
    }

    @JsonGetter("movieName")
    public String getMovieName() {
        return movieName;
    }

    @JsonGetter("hallName")
    public String getHallName() {
        return hallName;
    }

    @JsonGetter("sessionTime")
    public String getSessionTime() {
        return sessionTime;
    }

    @JsonGetter("price")
    public int getPrice() {
        return price;
    }
}
