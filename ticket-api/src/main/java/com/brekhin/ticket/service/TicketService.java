package com.brekhin.ticket.service;

import com.brekhin.ticket.entity.TicketEntity;

public interface TicketService {

    Long addTicket(TicketEntity ticketEntity);

    Long deleteTicketBuId(Long id);

    TicketEntity getTicketById(Long id);
}
