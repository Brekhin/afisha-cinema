package com.brekhin.gateway.web.to.in.ticket;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketBuildStep2 {

    private final Long ticketId;
    private final TicketBuildStep1 ticketBuildStep1;
    private final int row;
    private final int col;

    public TicketBuildStep2(@JsonProperty("ticketId") Long ticketId,
                            @JsonProperty("ticketBuildStep1") TicketBuildStep1 ticketBuildStep1,
                            @JsonProperty("row") int row,
                            @JsonProperty("column") int col) {
        this.ticketId = ticketId;
        this.ticketBuildStep1 = ticketBuildStep1;
        this.col = col;
        this.row = row;
    }

    @JsonGetter("ticketId")
    public Long getTicketId() {
        return ticketId;
    }

    @JsonGetter("ticketBuildStep1")
    public TicketBuildStep1 getTicketBuildStep1() {
        return ticketBuildStep1;
    }

    @JsonGetter("row")
    public int getRow() {
        return row;
    }

    @JsonGetter("column")
    public int getCol() {
        return col;
    }
}
