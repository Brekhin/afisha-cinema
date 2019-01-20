package com.brekhin.ticket.service;

import com.brekhin.ticket.entity.TicketEntity;

public interface TicketService {

    Long addTicket(TicketEntity ticketEntity);

    Long deleteTicketById(Long id);

    TicketEntity getTicketById(Long id);

    void deleteTicketsBySessionId(Long sessionId);
}
