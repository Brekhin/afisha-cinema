package com.brekhin.gateway.converter;

import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.out.moviesession.CinemaHallTO;
import com.brekhin.gateway.web.to.out.moviesession.InfoTimeOfSessionResponse;
import com.brekhin.moviesession.grpc.model.GCinemaHall;
import com.brekhin.moviesession.grpc.model.GRpcAddSessionRequest;
import com.brekhin.moviesession.grpc.model.GRpcGetSessionsByMovieIdRequest;
import com.brekhin.moviesession.grpc.model.GSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieSessionConverter {
    private final static Logger log = LoggerFactory.getLogger(MovieSessionConverter.class);

    public static GRpcAddSessionRequest convert(AddTimeOfSessionRequest gTimeOfSession) {
        return GRpcAddSessionRequest.newBuilder()
                .setSession(GSession.newBuilder()
                        .setTimeOfSession(gTimeOfSession.getTimeOfSession())
                        .setMovieId(gTimeOfSession.getMovieId())
                        .setPrice(gTimeOfSession.getPrice())
                        .setHallId(gTimeOfSession.getHallId())
                        .build())
                .build();
    }

    public static GRpcGetSessionsByMovieIdRequest convert(Long id) {
        return GRpcGetSessionsByMovieIdRequest.newBuilder()
                .setMovieId(id)
                .build();
    }

    public static InfoTimeOfSessionResponse convert(GSession request) {
        return new InfoTimeOfSessionResponse(
                request.getSessionId(),
                request.getTimeOfSession(),
                request.getMovieId(),
                request.getPrice(),
                request.getHallId()
        );
    }

    public static CinemaHallTO convert(GCinemaHall gCinemaHall) {
        return new CinemaHallTO(
                gCinemaHall.getHallId(),
                gCinemaHall.getName(),
                gCinemaHall.getSeatCount());
    }

    public static GCinemaHall convert(CinemaHallTO cinemaHall) {
        return GCinemaHall.newBuilder()
                .setName(cinemaHall.getName())
                .setSeatCount(cinemaHall.getSeatCount())
                .build();
    }
}
