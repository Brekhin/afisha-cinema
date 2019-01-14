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

    public gRPCAddTimeOfSessionResponse addTimeOfSession(gRPCAddTimeOfSessionRequest request) {
        return movieSessionServiceBlockingStub.addTimeOfSession(request);
    }

    public gRPCGetInfoTimeOfSessionByIdResponse getInfoTimeOfSessionById(gRPCGetInfoTimeOfSessionByIdRequest request) {
        return movieSessionServiceBlockingStub.getInfoAboutTimeOfSessionById(request);
    }

    public gRPCGetSessionsByMovieIdResponse getSessionsByMovieId(gRPCGetSessionsByMovieIdRequest request) {
        return movieSessionServiceBlockingStub.getSessionsByMovieId(request);
    }

    public Empty deletSessionById(gRPCDeleteSessionByIdRequest request) {
        return movieSessionServiceBlockingStub.deleteSessionById(request);
    }

    public Empty deleteAllSessionsByMovieId(gRPCDeleteAllSessionsByMovieIdRequest request) {
        return movieSessionServiceBlockingStub.deleteAllSessionsByMovieId(request);
    }

    public gRPCGetAllCinemaHallResponse getAllCinemaHall(gRPCGetAllCinemaHallRequest request) {
        return movieSessionServiceBlockingStub.getAllCinemaHall(request);
    }


    public gRPCAddCinemaHallResponse addCinemaHall(gRPCAddCinemaHallRequest request) {
        return movieSessionServiceBlockingStub.addCinemaHall(request);
    }


    public gRPCDeleteCinemaHallByIdResponse deleteCinemaHallById(gRPCDeleteCinemaHallByIdRequest request) {
        return movieSessionServiceBlockingStub.deleteCinemaHallById(request);
    }


    public gRPCGetCinemaHallByIdResponse getCinemaHallById(gRPCGetCinemaHallByIdRequest request) {
        return movieSessionServiceBlockingStub.getCinemaHallById(request);
    }


    public Empty assignHallAndSession(gRPCAssignHallAndSessionRequest request) {
        return movieSessionServiceBlockingStub.assignHallAndSession(request);
    }
}
