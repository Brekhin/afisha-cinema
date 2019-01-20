package com.brekhin.gateway.grpc.client;

import com.brekhin.grpc.service.impl.TicketServiceGrpc;
import com.brekhin.movie.grpc.Empty;
import com.brekhin.ticket.grpc.model.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GRpcTicketServiceClient {

    private TicketServiceGrpc.TicketServiceBlockingStub ticketServiceBlockingStub;

    @Value("#{new String('${gateway.grpc.client.TicketServiceGrpc.host}')}")
    private String host;

    @Value("#{new Integer('${gateway.grpc.client.TicketServiceGrpc.port}')}")
    private Integer port;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        ticketServiceBlockingStub = TicketServiceGrpc.newBlockingStub(managedChannel);
    }

    public gRPCAddTicketResponse addTicket(gRPCAddTicketRequest request) {
        return ticketServiceBlockingStub.addTicket(request);
    }

    public gRPCDeleteTicketByIdResponse deleteTicketById(gRPCDeleteTicketByIdRequest request) {
        return ticketServiceBlockingStub.deleteTicketById(request);
    }

    public gRPCGetTicketByIdResponse getTicketById(gRPCGetTicketByIdRequest request) {
        return ticketServiceBlockingStub.getTicketById(request);
    }

    public Empty deleteTicketsBySessionId(gRPCDeleteTicketsBySessionIdRequest request){
        return ticketServiceBlockingStub.deleteTicketsBySessionId(request);
    }
}
