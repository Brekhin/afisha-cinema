package com.brekhin.gateway.web.to.in.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketInfo {

    private final Long ticketId;
    private final Long sessionId;
    private final int row;
    private final int col;

    public TicketInfo(@JsonProperty("ticketId") Long ticketId,
                      @JsonProperty("sessionId") Long sessionId,
                      @JsonProperty("row") int row,
                      @JsonProperty("column") int col) {
        this.ticketId = ticketId;
        this.sessionId = sessionId;
        this.row = row;
        this.col = col;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
