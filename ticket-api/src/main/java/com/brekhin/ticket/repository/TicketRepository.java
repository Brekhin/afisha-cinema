package com.brekhin.ticket.repository;

import com.brekhin.ticket.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    @Query(
            value = "select * from ticket_api.tickets ticket where " +
                    "ticket.row = ?1 and ticket.column = ?2 and ticket.sessionId = ?3",
            nativeQuery = true
    )
    TicketEntity checkSeat(int row, int col, Long sessionId);

    @Transactional
    @Modifying
    @Query(value = "delete from ticket_api.tickets ticket where ticket.sessionId = ?1",
    nativeQuery = true)
    void deleteTicketsBySessionId(Long sessionId);
}
