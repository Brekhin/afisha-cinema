package com.brekhin.moviesession.grpc.service.impl;

import com.brekhin.movie.grpc.Empty;
import com.brekhin.moviesession.converter.ProtoConvertToEntity;
import com.brekhin.moviesession.entity.CinemaHallEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import com.brekhin.moviesession.exception.NotFoundSessionException;
import com.brekhin.moviesession.grpc.model.*;
import com.brekhin.moviesession.grpc.service.MovieSessionServiceGrpc;
import com.brekhin.moviesession.service.CinemaHallService;
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
    private final CinemaHallService cinemaHallService;

    @Autowired
    public MovieSessionServiceGrpcImpl(CinemaSessionService cinemaSessionService,
                                       CinemaHallService cinemaHallService) {
        this.cinemaSessionService = cinemaSessionService;
        this.cinemaHallService = cinemaHallService;
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

    @Override
    public void getInfoAboutTimeOfSessionById(gRPCGetInfoTimeOfSessionByIdRequest request, StreamObserver<gRPCGetInfoTimeOfSessionByIdResponse> responseObserver) {
        try {
            TimeOfSessionEntity getTimeOfSessionById = cinemaSessionService.getTimeOfSessionById(request.getTimeOfSessionId());
            responseObserver.onNext(gRPCGetInfoTimeOfSessionByIdResponse.newBuilder()
                    .setTimeOfSessions(ProtoConvertToEntity.convert(getTimeOfSessionById))
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            onError(responseObserver, e);
        }
    }

    public void getSessionsByMovieId(gRPCGetSessionsByMovieIdRequest request, StreamObserver<gRPCGetSessionsByMovieIdResponse> response) {
        try {
            Collection<TimeOfSessionEntity> sessions = cinemaSessionService.getSessionsByMovieId(request.getMovieId());
            response.onNext(gRPCGetSessionsByMovieIdResponse.newBuilder()
                    .addAllSessions(sessions.stream()
                            .map(ProtoConvertToEntity::convert)
                            .collect(Collectors.toList()))
                    .build());
            response.onCompleted();
        } catch (Exception e) {
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

    @Override
    public void getAllCinemaHall(gRPCGetAllCinemaHallRequest request, StreamObserver<gRPCGetAllCinemaHallResponse> responseObserver) {
        try {
            List<CinemaHallEntity> allCinemaHall = cinemaHallService.getAllCinemaHall();
            responseObserver.onNext(gRPCGetAllCinemaHallResponse.newBuilder()
                    .addAllGCinemaHall(allCinemaHall.stream()
                            .map(ProtoConvertToEntity::convert)
                            .collect(Collectors.toList()))
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            onError(responseObserver, e);
        }
    }

    @Override
    public void addCinemaHall(gRPCAddCinemaHallRequest request, StreamObserver<gRPCAddCinemaHallResponse> responseObserver) {
        try {
            Long id = cinemaHallService.addCinemaHall(ProtoConvertToEntity.convert(request.getCinemaHall()));
            responseObserver.onNext(gRPCAddCinemaHallResponse.newBuilder()
                    .setHallId(id)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            onError(responseObserver, e);
        }
    }

    @Override
    public void deleteCinemaHallById(gRPCDeleteCinemaHallByIdRequest request, StreamObserver<gRPCDeleteCinemaHallByIdResponse> responseObserver) {
        try {
            Long id = cinemaHallService.deleteCinemaHallById(request.getHallId());
            responseObserver.onNext(gRPCDeleteCinemaHallByIdResponse.newBuilder()
                    .setHallId(id)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            onError(responseObserver, e);
        }
    }

    @Override
    public void getCinemaHallById(gRPCGetCinemaHallByIdRequest request, StreamObserver<gRPCGetCinemaHallByIdResponse> responseObserver) {
        try {
            CinemaHallEntity cinemaHall = cinemaHallService.getCinemaHallById(request.getHallId());
            responseObserver.onNext(gRPCGetCinemaHallByIdResponse.newBuilder()
                    .setGCinemaHall(ProtoConvertToEntity.convert(cinemaHall))
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            onError(responseObserver, e);
        }
    }

    @Override
    public void assignHallAndSession(gRPCAssignHallAndSessionRequest request, StreamObserver<Empty> responseObserver) {
        try {
            cinemaSessionService.assignHallAndSession(request.getHallId(), request.getSessionId());
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
