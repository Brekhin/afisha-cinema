package com.brekhin.gateway.service.impl;

import com.brekhin.gateway.converter.MovieConverter;
import com.brekhin.gateway.converter.MovieSessionConverter;
import com.brekhin.gateway.converter.TicketConverter;
import com.brekhin.gateway.grpc.client.GRpcMovieServiceClient;
import com.brekhin.gateway.grpc.client.GRpcMovieSessionServiceClient;
import com.brekhin.gateway.grpc.client.GRpcTicketServiceClient;
import com.brekhin.gateway.service.TicketService;
import com.brekhin.gateway.web.to.in.ticket.TicketBuildStep2;
import com.brekhin.gateway.web.to.in.ticket.TicketInfo;
import com.brekhin.gateway.web.to.out.movie.GetMovie;
import com.brekhin.gateway.web.to.out.moviesession.CinemaHallTO;
import com.brekhin.gateway.web.to.out.moviesession.InfoTimeOfSessionResponse;
import com.brekhin.gateway.web.to.out.ticket.TicketTarget;
import com.brekhin.movie.grpc.model.gRPCGetMovieRequest;
import com.brekhin.moviesession.grpc.model.GRpcGetCinemaHallByIdRequest;
import com.brekhin.moviesession.grpc.model.GRpcGetInfoAboutSessionByIdRequest;
import com.brekhin.ticket.grpc.model.gRPCDeleteTicketByIdRequest;
import com.brekhin.ticket.grpc.model.gRPCDeleteTicketsBySessionIdRequest;
import com.brekhin.ticket.grpc.model.gRPCGetTicketByIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private final GRpcTicketServiceClient gRpcTicketServiceClient;
    private final GRpcMovieSessionServiceClient gRpcMovieSessionServiceClient;
    private final GRpcMovieServiceClient gRpcMovieServiceClient;

    @Autowired
    public TicketServiceImpl(GRpcTicketServiceClient gRpcTicketServiceClient,
                             GRpcMovieSessionServiceClient gRpcMovieSessionServiceClient,
                             GRpcMovieServiceClient gRpcMovieServiceClient) {
        this.gRpcTicketServiceClient = gRpcTicketServiceClient;
        this.gRpcMovieSessionServiceClient = gRpcMovieSessionServiceClient;
        this.gRpcMovieServiceClient = gRpcMovieServiceClient;
    }

    @Override
    public Long addTicket(TicketBuildStep2 ticket) {
        return gRpcTicketServiceClient.addTicket(TicketConverter.convert(ticket)).getTicketId();
    }

    @Override
    public Long deleteTicketById(Long id) {
        return gRpcTicketServiceClient.deleteTicketById(gRPCDeleteTicketByIdRequest.newBuilder()
                .setTicketId(id)
                .build())
                .getTicketId();
    }

    @Override
    public TicketTarget getTicketById(Long id) {
        TicketInfo ticketInfo = TicketConverter.convert(gRpcTicketServiceClient.getTicketById(gRPCGetTicketByIdRequest.newBuilder()
                .setTicketId(id)
                .build()).getGTicket());

        InfoTimeOfSessionResponse session = MovieSessionConverter.convert(gRpcMovieSessionServiceClient.getInfoTimeOfSessionById(GRpcGetInfoAboutSessionByIdRequest.newBuilder()
                .setSessionId(ticketInfo.getSessionId())
                .build()).getTimeOfSessions());

        GetMovie movie = MovieConverter.convert(gRpcMovieServiceClient.getMovie(gRPCGetMovieRequest.newBuilder()
                .setMovieId(session.getMovieId())
                .build()).getMovie());

        CinemaHallTO cinemaHall = MovieSessionConverter.convert(gRpcMovieSessionServiceClient.getCinemaHallById(GRpcGetCinemaHallByIdRequest.newBuilder()
                .setHallId(session.getHallId())
                .build()).getGCinemaHall());

        return new TicketTarget(ticketInfo.getTicketId(), movie.getName(), ticketInfo.getCol(), ticketInfo.getRow(),
                cinemaHall.getName(), session.getTimeOfSession(), session.getPrice());
    }

    @Override
    public void deleteTicketsBySessionId(Long sessionId) {
        gRpcTicketServiceClient.deleteTicketsBySessionId(gRPCDeleteTicketsBySessionIdRequest.newBuilder()
                .setSessionId(sessionId)
                .build());
    }
}
