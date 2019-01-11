package com.brekhin.moviesession.grpc.service.impl;

import com.brekhin.movie.grpc.Empty;
import com.brekhin.moviesession.converter.ProtoConvertToEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import com.brekhin.moviesession.exception.NotFoundSessionException;
import com.brekhin.moviesession.grpc.model.*;
import com.brekhin.moviesession.grpc.service.MovieSessionServiceGrpc;
import com.brekhin.moviesession.service.CinemaSessionService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@GRpcService
public class MovieSessionServiceGrpcImpl extends MovieSessionServiceGrpc.MovieSessionServiceImplBase {

    private final static Logger log = LoggerFactory.getLogger(MovieSessionServiceGrpcImpl.class);
    private final CinemaSessionService cinemaSessionService;

    @Autowired
    public MovieSessionServiceGrpcImpl(CinemaSessionService cinemaSessionService) {
        this.cinemaSessionService = cinemaSessionService;
    }

    public void addTimeOfSession(gRPCAddTimeOfSessionRequest request, StreamObserver<gRPCAddTimeOfSessionResponse> response) {
        try {
            Long timeOfSession = cinemaSessionService.addTimeOfSession(ProtoConvertToEntity.convert(request.getTimeOfSession()));
            response.onNext(gRPCAddTimeOfSessionResponse.newBuilder().setTimeOfSessionId(timeOfSession).build());
            response.onCompleted();
        } catch (Exception e) {
            onError(response, e);
        }
    }

    public void getTimeOfSessionById(gRPCGetInfoTimeOfSessionByIdRequest request,
                                     StreamObserver<gRPCGetInfoTimeOfSessionByIdResponse> response) {
        try {
            TimeOfSessionEntity getTimeOfSessionById = cinemaSessionService.getTimeOfSessionById(request.getTimeOfSessionId());
            response.onNext(gRPCGetInfoTimeOfSessionByIdResponse.newBuilder()
                    .setTimeOfSessions(ProtoConvertToEntity.convert(getTimeOfSessionById))
                    .build());
            response.onCompleted();
        } catch (Exception e) {
            onError(response, e);
        }
    }


    public void getSessionsByMovieId(gRPCGetSessionsByMovieIdRequest request, StreamObserver<gRPCGetSessionsByMovieIdResponse> response) {
        try{
            Collection<TimeOfSessionEntity> sessions = cinemaSessionService.getSessionsByMovieId(request.getMovieId());
            response.onNext(gRPCGetSessionsByMovieIdResponse.newBuilder()
                    .addAllSessions(sessions.stream()
                            .map(ProtoConvertToEntity::convert)
                            .collect(Collectors.toList()))
                    .build());
            response.onCompleted();
        }catch (Exception e) {
            onError(response, e);
        }
    }

    @Override
    public void deleteSessionById(gRPCDeleteSessionByIdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            cinemaSessionService.deleteSessionById(request.getSessionId());
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            onError(responseObserver, e);
        }
    }

    @Override
    public void deleteAllSessionsByMovieId(gRPCDeleteAllSessionsByMovieIdRequest request, StreamObserver<Empty> responseObserver) {
        try {
            cinemaSessionService.deleteSessionsByMovieId(request.getMovieId());
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            onError(responseObserver, e);
        }
    }

    private void onError(StreamObserver<?> responseObserver, Exception e) {
        Status status;

        if (e instanceof IllegalArgumentException) {
            status = Status.INVALID_ARGUMENT;
        } else if (e instanceof NotFoundSessionException) {
            status = Status.NOT_FOUND;
        } else {
            status = Status.INTERNAL;
        }

        responseObserver.onError(status
                .withDescription(e.getMessage())
                .asRuntimeException());
    }
}
