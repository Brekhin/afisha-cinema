package com.brekhin.gateway.grpc.client;

import com.brekhin.movie.grpc.Empty;
import com.brekhin.movie.grpc.model.gRPCAssignMovieWithSessionRequest;
import com.brekhin.movie.grpc.service.MovieServiceGrpc;
import com.brekhin.moviesession.grpc.model.*;
import com.brekhin.moviesession.grpc.service.MovieSessionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GRpcMovieSessionServiceClient {

    private MovieSessionServiceGrpc.MovieSessionServiceBlockingStub movieSessionServiceBlockingStub;

    @Value("#{new String('${gateway.grpc.client.MovieSessionServiceGrpc.host}')}")
    private String host;

    @Value("#{new Integer('${gateway.grpc.client.MovieSessionServiceGrpc.port}')}")
    private Integer port;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        movieSessionServiceBlockingStub = MovieSessionServiceGrpc.newBlockingStub(managedChannel);
    }

    public GRpcAddSessionResponse addTimeOfSession(GRpcAddSessionRequest request) {
        return movieSessionServiceBlockingStub.addSession(request);
    }

    public GRpcGetInfoAboutSessionByIdResponse getInfoTimeOfSessionById(GRpcGetInfoAboutSessionByIdRequest request) {
        return movieSessionServiceBlockingStub.getInfoAboutSessionById(request);
    }

    public GRpcGetSessionsByMovieIdResponse getSessionsByMovieId(GRpcGetSessionsByMovieIdRequest request) {
        return movieSessionServiceBlockingStub.getSessionsByMovieId(request);
    }

    public Empty deletSessionById(GRpcDeleteSessionByIdRequest request) {
        return movieSessionServiceBlockingStub.deleteSessionById(request);
    }

    public Empty deleteAllSessionsByMovieId(GRpcDeleteAllSessionsByMovieIdRequest request) {
        return movieSessionServiceBlockingStub.deleteAllSessionsByMovieId(request);
    }

    public GRpcGetAllCinemaHallResponse getAllCinemaHall(GRpcGetAllCinemaHallRequest request) {
        return movieSessionServiceBlockingStub.getAllCinemaHall(request);
    }


    public GRpcAddCinemaHallResponse addCinemaHall(GRpcAddCinemaHallRequest request) {
        return movieSessionServiceBlockingStub.addCinemaHall(request);
    }


    public GRpcDeleteCinemaHallByIdResponse deleteCinemaHallById(GRpcDeleteCinemaHallByIdRequest request) {
        return movieSessionServiceBlockingStub.deleteCinemaHallById(request);
    }


    public GRpcGetCinemaHallByIdResponse getCinemaHallById(GRpcGetCinemaHallByIdRequest request) {
        return movieSessionServiceBlockingStub.getCinemaHallById(request);
    }

    public Empty assignHallAndSession(GRpcAssignHallAndSessionRequest request) {
        return movieSessionServiceBlockingStub.assignHallAndSession(request);
    }
}
