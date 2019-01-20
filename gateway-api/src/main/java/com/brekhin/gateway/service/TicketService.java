package com.brekhin.gateway.service;

import com.brekhin.gateway.web.to.in.ticket.TicketBuildStep2;
import com.brekhin.gateway.web.to.out.ticket.TicketTarget;

public interface TicketService {

    Long addTicket(TicketBuildStep2 ticket);

    Long deleteTicketById(Long id);

    TicketTarget getTicketById(Long id);

    void deleteTicketsBySessionId(Long sessionId);
}
