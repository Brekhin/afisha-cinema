package com.brekhin.gateway.converter;

import com.brekhin.gateway.web.to.in.ticket.TicketBuildStep2;
import com.brekhin.gateway.web.to.in.ticket.TicketInfo;
import com.brekhin.ticket.grpc.model.GTicket;
import com.brekhin.ticket.grpc.model.gRPCAddTicketRequest;

public class TicketConverter {

    public static gRPCAddTicketRequest convert(TicketBuildStep2 request) {
        return gRPCAddTicketRequest.newBuilder()
                .setGTicket(GTicket.newBuilder()
                        .setTicketId(request.getTicketId())
                        .setSessionId(request.getTicketBuildStep1().getSessionId())
                        .setCol(request.getCol())
                        .setRow(request.getRow()))
                .build();
    }

    public static TicketInfo convert(GTicket ticket) {
        return new TicketInfo(
                ticket.getTicketId(),
                ticket.getSessionId(),
                ticket.getCol(),
                ticket.getRow());
    }
}
