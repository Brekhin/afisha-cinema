package com.brekhin.gateway.web.to.in.ticket;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class TicketBuildStep1 {

    private Long sessionId;
    private String movieName;
    private String hallName;
    private Timestamp time;
    private int price;

    public TicketBuildStep1() {
        super();
    }

    public TicketBuildStep1(
            @JsonProperty("sessionId") Long sessionId,
            @JsonProperty("movieName") String movieName,
            @JsonProperty("hallName") String hallName,
            @JsonProperty("sessionTime") Timestamp time,
            @JsonProperty("price") int price) {
        this.sessionId = sessionId;
        this.movieName = movieName;
        this.hallName = hallName;
        this.time = time;
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

    @JsonGetter("time")
    public Timestamp getTime() {
        return time;
    }

    @JsonGetter("price")
    public int getPrice() {
        return price;
    }
}
