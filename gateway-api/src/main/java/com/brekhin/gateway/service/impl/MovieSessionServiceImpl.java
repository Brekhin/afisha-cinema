package com.brekhin.gateway.service.impl;

import com.brekhin.gateway.converter.MovieSessionConverter;
import com.brekhin.gateway.grpc.client.GRpcMovieServiceClient;
import com.brekhin.gateway.grpc.client.GRpcMovieSessionServiceClient;
import com.brekhin.gateway.service.MovieSessionService;
import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.in.ticket.TicketBuildStep1;
import com.brekhin.gateway.web.to.out.moviesession.CinemaHallTO;
import com.brekhin.gateway.web.to.out.moviesession.InfoTimeOfSessionResponse;
import com.brekhin.movie.grpc.model.gRPCGetMovieRequest;
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
    private final GRpcMovieServiceClient gRpcMovieServiceClient;

    @Autowired
    public MovieSessionServiceImpl(GRpcMovieSessionServiceClient gRpcMovieSessionServiceClient,
                                   GRpcMovieServiceClient gRpcMovieServiceClient) {
        this.gRpcMovieSessionServiceClient = gRpcMovieSessionServiceClient;
        this.gRpcMovieServiceClient = gRpcMovieServiceClient;
    }


    @Override
    public Long addTimeOfSession(AddTimeOfSessionRequest request) {
        return gRpcMovieSessionServiceClient.addTimeOfSession(MovieSessionConverter.convert(request)).getSessionId();
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
    public TicketBuildStep1 getInfoTimeOfSessionById(Long timeOfSessionId) {
        InfoTimeOfSessionResponse infoAboutSession = MovieSessionConverter.convert(
                gRpcMovieSessionServiceClient.getInfoTimeOfSessionById(
                        GRpcGetInfoAboutSessionByIdRequest.newBuilder()
                                .setSessionId(timeOfSessionId)
                                .build())
                        .getTimeOfSessions());
        Long movieId = infoAboutSession.getMovieId();
        Long hallId = infoAboutSession.getHallId();
        String movieName = gRpcMovieServiceClient.getMovie(gRPCGetMovieRequest.newBuilder()
                .setMovieId(movieId)
                .build()).getMovie().getName();
        String hallName = gRpcMovieSessionServiceClient.getCinemaHallById(GRpcGetCinemaHallByIdRequest.newBuilder()
                .setHallId(hallId)
                .build())
                .getGCinemaHall()
                .getName();
        String timeOfSession = infoAboutSession.getTimeOfSession();
        int price = infoAboutSession.getPrice();

        return new TicketBuildStep1(timeOfSessionId, movieName,hallName,timeOfSession, price);
    }

    @Override
    public void deleteSessionById(Long id) {
        gRpcMovieSessionServiceClient.deletSessionById(GRpcDeleteSessionByIdRequest.newBuilder()
                .setSessionId(id)
                .build());
    }

    @Override
    public void deleteAllSessionsByMovieId(Long movieId) {
        gRpcMovieSessionServiceClient.deleteAllSessionsByMovieId(GRpcDeleteAllSessionsByMovieIdRequest.newBuilder()
                .setMovieId(movieId)
                .build());
    }

    @Override
    public List<CinemaHallTO> getAllCinemaHall() {
        return gRpcMovieSessionServiceClient.getAllCinemaHall(GRpcGetAllCinemaHallRequest.newBuilder().build())
                .getGCinemaHallList()
                .stream()
                .map(MovieSessionConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Long addCinemaHall(CinemaHallTO cinemaHall) {
        return gRpcMovieSessionServiceClient.addCinemaHall(GRpcAddCinemaHallRequest.newBuilder()
                .setCinemaHall(MovieSessionConverter.convert(cinemaHall))
                .build())
                .getHallId();
    }

    @Override
    public Long deleteCinemaHallById(Long id) {
        return gRpcMovieSessionServiceClient.deleteCinemaHallById(GRpcDeleteCinemaHallByIdRequest.newBuilder()
                .setHallId(id)
                .build())
                .getHallId();
    }

    @Override
    public CinemaHallTO getCinemaHallById(Long id) {
        return MovieSessionConverter.convert(gRpcMovieSessionServiceClient.getCinemaHallById(GRpcGetCinemaHallByIdRequest.newBuilder()
                .setHallId(id)
                .build())
                .getGCinemaHall());
    }
}
