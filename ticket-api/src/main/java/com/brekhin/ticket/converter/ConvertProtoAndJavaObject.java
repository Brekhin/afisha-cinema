package com.brekhin.ticket.converter;

import com.brekhin.ticket.entity.TicketEntity;
import com.brekhin.ticket.grpc.model.GTicket;

public class ConvertProtoAndJavaObject {

    public static GTicket convert(TicketEntity ticketEntity) {
        return GTicket.newBuilder()
                .setTicketId(ticketEntity.getTicketId())
                .setRow(ticketEntity.getRow())
                .setCol(ticketEntity.getCol())
                .setSessionId(ticketEntity.getSessionId())
                .build();
    }

    public static TicketEntity convert(GTicket gTicket) {
        return new TicketEntity()
                .setTicketId(gTicket.getTicketId())
                .setSessionId(gTicket.getSessionId())
                .setCol(gTicket.getCol())
                .setRow(gTicket.getRow());
    }
}
