package com.brekhin.gateway.grpc.client;

import com.brekhin.movie.grpc.Empty;
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

    public gRPCAddDateOfSessionResponse addDateOfSession(gRPCAddDateOfSessionRequest request) {
        return movieSessionServiceBlockingStub.addDateOfSession(request);
    }

    public gRPCGetAllDateOfSessionResponse getAllDateOfSession(gRPCGetAllDateOfSessionRequest request) {
        return movieSessionServiceBlockingStub.getAllDateOfSession(request);
    }

    public gRPCAddTimeOfSessionResponse addTimeOfSession(gRPCAddTimeOfSessionRequest request) {
        return movieSessionServiceBlockingStub.addTimeOfSession(request);
    }

    public gRPCGetAllSessionByDateResponse getAllSessionByDate(gRPCGetAllSessionByDateRequest request) {
        return movieSessionServiceBlockingStub.getAllSessionByDate(request);
    }

    public gRPCGetInfoAboutTimeOfSessionInSpecificDayResponse getInfoAboutTimeOfSessionInSpecificDay(gRPCGetInfoAboutTimeOfSessionInSpecificDayRequest request) {
        return movieSessionServiceBlockingStub.getInfoAboutTimeOfSessionInSpecificDay(request);
    }

    public gRPCAssigneTimeOfSessionsWithDayResponse assigneTimeOfSessionsWithDay(gRPCAssigneTimeOfSessionsWithDayRequest request) {
        return movieSessionServiceBlockingStub.assigneTimeOfSessionsWithDay(request);
    }

    public gRPCAssignMovieWithSessionResponse assignMovieWithSession(gRPCAssignMovieWithSessionRequest request) {
        return movieSessionServiceBlockingStub.assignMovieWithSession(request);
    }
}
