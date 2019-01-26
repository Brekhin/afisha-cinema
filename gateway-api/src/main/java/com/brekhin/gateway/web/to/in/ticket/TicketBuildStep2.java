package com.brekhin.gateway.web.to.in.ticket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TicketBuildStep2 {

    private final TicketBuildStep1 ticketBuildStep1;
    private final int row;
    private final int col;

    @JsonCreator
    public TicketBuildStep2(
                            @JsonProperty("ticketBuildStep1") TicketBuildStep1 ticketBuildStep1,
                            @JsonProperty("row") int row,
                            @JsonProperty("col") int col) {
        this.ticketBuildStep1 = ticketBuildStep1;
        this.col = col;
        this.row = row;
    }

    @JsonGetter("ticketBuildStep1")
    public TicketBuildStep1 getTicketBuildStep1() {
        return ticketBuildStep1;
    }

    @JsonGetter("row")
    public int getRow() {
        return row;
    }

    @JsonGetter("col")
    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
