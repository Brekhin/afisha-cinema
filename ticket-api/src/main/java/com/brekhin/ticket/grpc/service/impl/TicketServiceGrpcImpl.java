package com.brekhin.ticket.grpc.service.impl;

import com.brekhin.grpc.service.impl.TicketServiceGrpc;
import com.brekhin.movie.grpc.Empty;
import com.brekhin.ticket.converter.ConvertProtoAndJavaObject;
import com.brekhin.ticket.entity.TicketEntity;
import com.brekhin.ticket.grpc.model.*;
import com.brekhin.ticket.service.TicketService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class TicketServiceGrpcImpl extends TicketServiceGrpc.TicketServiceImplBase {

    private final TicketService ticketService;

    @Autowired
    public TicketServiceGrpcImpl(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public void addTicket(gRPCAddTicketRequest request, StreamObserver<gRPCAddTicketResponse> responseObserver) {
        try {
            Long id = ticketService.addTicket(ConvertProtoAndJavaObject.convert(request.getGTicket()));
            responseObserver.onNext(gRPCAddTicketResponse.newBuilder()
                    .setTicketId(id)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteTicketById(gRPCDeleteTicketByIdRequest request, StreamObserver<gRPCDeleteTicketByIdResponse> responseObserver) {
        try {
            Long id = ticketService.deleteTicketById(request.getTicketId());
            responseObserver.onNext(gRPCDeleteTicketByIdResponse.newBuilder()
                    .setTicketId(id)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTicketById(gRPCGetTicketByIdRequest request, StreamObserver<gRPCGetTicketByIdResponse> responseObserver) {
        try {
            TicketEntity ticket = ticketService.getTicketById(request.getTicketId());
            responseObserver.onNext(gRPCGetTicketByIdResponse.newBuilder()
                    .setGTicket(ConvertProtoAndJavaObject.convert(ticket))
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTicketsBySessionId(gRPCDeleteTicketsBySessionIdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            ticketService.deleteTicketsBySessionId(request.getSessionId());
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
