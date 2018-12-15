package com.brekhin.moviesession.grpc.service.impl;

import com.brekhin.moviesession.converter.ProtoConvertToEntity;
import com.brekhin.moviesession.entity.DateOfSessionEntity;
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

    public void addDateOfSession(gRPCAddDateOfSessionRequest request, StreamObserver<gRPCAddDateOfSessionResponse> response) {
        try {
            Long dateOfSessionId = cinemaSessionService.addDateOfSession(ProtoConvertToEntity.convert(request.getDateOfSession()));
            response.onNext(gRPCAddDateOfSessionResponse.newBuilder()
                    .setDateOfSessionId(dateOfSessionId)
                    .build());
            response.onCompleted();
        } catch (Exception e) {
            onError(response, e);
        }
    }

    public void getAllDateOfSession(gRPCGetAllDateOfSessionRequest request, StreamObserver<gRPCGetAllDateOfSessionResponse> response) {
        try {
            List<DateOfSessionEntity> allSessionsByDate = cinemaSessionService.getAllDateOfSessions();
            response.onNext(gRPCGetAllDateOfSessionResponse.newBuilder()
                    .addAllDateOfSession(
                            allSessionsByDate.stream()
                                    .map(ProtoConvertToEntity::convert)
                                    .collect(Collectors.toList()))
                    .build());
            response.onCompleted();
        } catch (Exception e) {
            onError(response, e);
        }
    }

    public void addTimeOfSession(gRPCAddTimeOfSessionRequest request, StreamObserver<gRPCAddTimeOfSessionResponse> response) {
        try {
            Long timeOfSession = cinemaSessionService.addTimeOfSession(ProtoConvertToEntity.convert(request.getTimeOfSession()));
          //  log.warn("dd" + ProtoConvertToEntity.convert(request.getTimeOfSession().getDateOfSession().getDateOfSessionId());
            response.onNext(gRPCAddTimeOfSessionResponse.newBuilder().setTimeOfSessionId(timeOfSession).build());
            response.onCompleted();
        } catch (Exception e) {
            onError(response, e);
        }
    }

    public void getAllSessionsByDate(gRPCGetAllSessionByDateRequest request, StreamObserver<gRPCGetAllSessionByDateResponse> response) {

        try {
            DateOfSessionEntity allSessionsByDate = cinemaSessionService.getAllSessionsByDate(request.getDateOfSessionId());
            response.onNext(gRPCGetAllSessionByDateResponse.newBuilder()
                    .setDateOfSession(ProtoConvertToEntity.convert(allSessionsByDate)).build());
            response.onCompleted();
        } catch (Exception e) {
            onError(response, e);
        }
    }

    public void getInfoAboutTimeOfSessionInSpecificDay(gRPCGetInfoAboutTimeOfSessionInSpecificDayRequest request,
                                                       StreamObserver<gRPCGetInfoAboutTimeOfSessionInSpecificDayResponse> response) {
        try {
            TimeOfSessionEntity infoAboutTimeOfSessionInSpecificDay = cinemaSessionService.getInfoAboutTimeOfSessionInSpecificDay(request.getTimeOfSessionId(), request.getDateOfSessionId());
            response.onNext(gRPCGetInfoAboutTimeOfSessionInSpecificDayResponse.newBuilder()
                    .setTimeOfSessions(ProtoConvertToEntity.convert(infoAboutTimeOfSessionInSpecificDay))
                    .build());
            response.onCompleted();
        } catch (Exception e) {
            onError(response, e);
        }
    }

    public void assigneTimeOfSessionsWithDay(gRPCAssigneTimeOfSessionsWithDayRequest request,
                                             StreamObserver<gRPCAssigneTimeOfSessionsWithDayResponse> response) {
        try {
            cinemaSessionService.assigneTimeOfSessionsWithDay(request.getTimeOfSessionId(), request.getDateOfSessionId());
            response.onNext(gRPCAssigneTimeOfSessionsWithDayResponse.newBuilder().build());
            response.onCompleted();
        } catch (Exception e) {
            onError(response, e);
        }
    }

    public void assignMovieWithSession(gRPCAssignMovieWithSessionRequest request, StreamObserver<gRPCAssignMovieWithSessionResponse> response) {
        try {
            cinemaSessionService.assignMovieWithSession(request.getTimeOfSessionId(), request.getMovieId());
            response.onNext(gRPCAssignMovieWithSessionResponse.newBuilder().build());
            response.onCompleted();
        } catch (Exception e) {
            onError(response, e);
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
