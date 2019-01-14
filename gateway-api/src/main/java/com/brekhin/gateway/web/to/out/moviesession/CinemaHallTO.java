package com.brekhin.gateway.web.to.out.moviesession;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CinemaHallTO {
    private final Long hallId;
    private final String name;
    private final int seatCount;

    public CinemaHallTO(@JsonProperty("hallId") Long hallId,
                        @JsonProperty("name") String name,
                        @JsonProperty("seatCount") int seatCount) {
        this.hallId = hallId;
        this.name = name;
        this.seatCount = seatCount;
    }

    @JsonGetter("hallId")
    public Long getHallId() {
        return hallId;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("seatCount")
    public int getSeatCount() {
        return seatCount;
    }
}
