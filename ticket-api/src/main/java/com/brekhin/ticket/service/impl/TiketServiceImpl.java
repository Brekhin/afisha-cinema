package com.brekhin.ticket.service.impl;

import com.brekhin.ticket.entity.TicketEntity;
import com.brekhin.ticket.exception.NotFoundTicketException;
import com.brekhin.ticket.repository.TicketRepository;
import com.brekhin.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TiketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TiketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Long addTicket(TicketEntity ticketEntity) {
        return ticketRepository.save(ticketEntity).getTicketId();
    }

    @Override
    public Long deleteTicketById(Long id) {
        Optional<TicketEntity> ticket = ticketRepository.findById(id);
        ticket.ifPresent(v -> ticketRepository.delete(v));
        return id;
    }

    @Override
    public TicketEntity getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> {
                    return new NotFoundTicketException("Not found ticket with id = " + id);
                });
    }

    @Override
    public void deleteTicketsBySessionId(Long sessionId) {
        ticketRepository.deleteTicketsBySessionId(sessionId);
    }

    public boolean checkSeat(int row, int column, Long sessionId) {
        return ticketRepository.checkSeat(row, column, sessionId).equals(null);
    }
}
