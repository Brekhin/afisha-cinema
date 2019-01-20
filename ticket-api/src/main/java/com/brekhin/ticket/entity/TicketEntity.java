package com.brekhin.ticket.entity;

import javax.persistence.*;

@Entity
@Table(name = "tickets", schema = "ticket_api")
public class TicketEntity {

    @Id
    private Long ticketId;
    private Long sessionId;
    private int col;
    private int row;

    @Column(name = "ticketId")
    public Long getTicketId() {
        return ticketId;
    }

    public TicketEntity setTicketId(Long ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    @Column(name = "sessionId")
    public Long getSessionId() {
        return sessionId;
    }

    public TicketEntity setSessionId(Long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    @Column(name = "column")
    public int getCol() {
        return col;
    }

    public TicketEntity setCol(int col) {
        this.col = col;
        return this;
    }

    @Column(name = "row")
    public int getRow() {
        return row;
    }

    public TicketEntity setRow(int row) {
        this.row = row;
        return this;
    }
}
