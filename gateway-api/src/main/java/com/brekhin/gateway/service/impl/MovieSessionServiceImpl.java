package com.brekhin.gateway.service.impl;

import com.brekhin.gateway.converter.MovieSessionConverter;
import com.brekhin.gateway.grpc.client.GRpcMovieSessionServiceClient;
import com.brekhin.gateway.service.MovieSessionService;
import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.out.moviesession.CinemaHallTO;
import com.brekhin.gateway.web.to.out.moviesession.InfoTimeOfSessionResponse;
import com.brekhin.moviesession.grpc.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {

    private final static Logger log = LoggerFactory.getLogger(MovieSessionServiceImpl.class);
    private final GRpcMovieSessionServiceClient gRpcMovieSessionServiceClient;

    @Autowired
    public MovieSessionServiceImpl(GRpcMovieSessionServiceClient gRpcMovieSessionServiceClient) {
        this.gRpcMovieSessionServiceClient = gRpcMovieSessionServiceClient;
    }


    @Override
    public Long addTimeOfSession(AddTimeOfSessionRequest request) {
        return gRpcMovieSessionServiceClient.addTimeOfSession(MovieSessionConverter.convert(request)).getTimeOfSessionId();
    }

    @Override
    public InfoTimeOfSessionResponse getInfoTimeOfSessionById(Long timeOfSessionId) {
        return MovieSessionConverter.convert(
                gRpcMovieSessionServiceClient.getInfoTimeOfSessionById(
                        gRPCGetInfoTimeOfSessionByIdRequest.newBuilder()
                                .setTimeOfSessionId(timeOfSessionId)
                                .build())
                        .getTimeOfSessions());
    }

    @Override
    public List<InfoTimeOfSessionResponse> getSessionsByMovieId(Long movieId) {
        return gRpcMovieSessionServiceClient.getSessionsByMovieId(MovieSessionConverter.convert(movieId))
                .getSessionsList()
                .stream()
                .map(MovieSessionConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSessionById(Long id) {
        gRpcMovieSessionServiceClient.deletSessionById(gRPCDeleteSessionByIdRequest.newBuilder()
                .setSessionId(id)
                .build());
    }

    @Override
    public void deleteAllSessionsByMovieId(Long movieId) {
        gRpcMovieSessionServiceClient.deleteAllSessionsByMovieId(gRPCDeleteAllSessionsByMovieIdRequest.newBuilder()
                .setMovieId(movieId)
                .build());
    }

    @Override
    public void assignHallAndSession(Long hallId, Long sessionId) {
        gRpcMovieSessionServiceClient.assignHallAndSession(gRPCAssignHallAndSessionRequest.newBuilder()
                .setHallId(hallId)
                .setSessionId(sessionId)
                .build());
    }

    @Override
    public List<CinemaHallTO> getAllCinemaHall() {
        return gRpcMovieSessionServiceClient.getAllCinemaHall(gRPCGetAllCinemaHallRequest.newBuilder().build())
                .getGCinemaHallList()
                .stream()
                .map(MovieSessionConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Long addCinemaHall(CinemaHallTO cinemaHall) {
        return gRpcMovieSessionServiceClient.addCinemaHall(gRPCAddCinemaHallRequest.newBuilder()
                .setCinemaHall(MovieSessionConverter.convert(cinemaHall))
                .build())
                .getHallId();
    }

    @Override
    public Long deleteCinemaHallById(Long id) {
        return gRpcMovieSessionServiceClient.deleteCinemaHallById(gRPCDeleteCinemaHallByIdRequest.newBuilder()
                .setHallId(id)
                .build())
                .getHallId();
    }

    @Override
    public CinemaHallTO getCinemaHallById(Long id) {
        return MovieSessionConverter.convert(gRpcMovieSessionServiceClient.getCinemaHallById(gRPCGetCinemaHallByIdRequest.newBuilder()
                .setHallId(id)
                .build())
                .getGCinemaHall());
    }


}
