package com.brekhin.gateway.web.to.out.ticket;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class TicketTarget {

    private final Long ticketId;
    private final String movieName;
    private final int columnHall;
    private final int rowHall;
    private final String hallName;
    private final Timestamp time;
    private final int price;

    public TicketTarget(Long ticketId,
                        String movieName,
                        int columnHall,
                        int rowHall,
                        String hallName,
                        Timestamp time,
                        int price) {
        this.ticketId = ticketId;
        this.movieName = movieName;
        this.columnHall = columnHall;
        this.rowHall = rowHall;
        this.hallName = hallName;
        this.time = time;
        this.price = price;
    }

    @JsonGetter("ticketId")
    public Long getTicketId() {
        return ticketId;
    }

    @JsonGetter("movieName")
    public String getMovieName() {
        return movieName;
    }

    @JsonGetter("column")
    public int getColumnHall() {
        return columnHall;
    }

    @JsonGetter("row")
    public int getRowHall() {
        return rowHall;
    }

    @JsonGetter("hallName")
    public String getHallName() {
        return hallName;
    }

    @JsonGetter("timeOfSession")
    public Timestamp getTime() {
        return time;
    }

    @JsonGetter("price")
    public int getPrice() {
        return price;
    }
}
