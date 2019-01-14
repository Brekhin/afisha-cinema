package com.brekhin.gateway.converter;

import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.out.moviesession.CinemaHallTO;
import com.brekhin.gateway.web.to.out.moviesession.InfoTimeOfSessionResponse;
import com.brekhin.moviesession.grpc.model.GCinemaHall;
import com.brekhin.moviesession.grpc.model.GTimeOfSession;
import com.brekhin.moviesession.grpc.model.gRPCAddTimeOfSessionRequest;
import com.brekhin.moviesession.grpc.model.gRPCGetSessionsByMovieIdRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class MovieSessionConverter {
    private final static Logger log = LoggerFactory.getLogger(MovieSessionConverter.class);

    public static gRPCAddTimeOfSessionRequest convert(AddTimeOfSessionRequest gTimeOfSession) {
        return gRPCAddTimeOfSessionRequest.newBuilder()
                .setTimeOfSession(GTimeOfSession.newBuilder()
                        .setTimeOfSessionId(gTimeOfSession.getTimeOfSessionId())
                        .setTimeOfSessionDate(gTimeOfSession.getTimeOfSessionDate().getTime())
                        .setMovieId(gTimeOfSession.getMovieId())
                        .setPrice(gTimeOfSession.getPrice())
                        .setHallId(gTimeOfSession.getHallId())
                        .build())
                .build();
    }

    public static gRPCGetSessionsByMovieIdRequest convert(Long id) {
        return gRPCGetSessionsByMovieIdRequest.newBuilder()
                .setMovieId(id)
                .build();
    }

    public static InfoTimeOfSessionResponse convert(GTimeOfSession request) {
        return new InfoTimeOfSessionResponse(
                request.getTimeOfSessionId(),
                new Timestamp(request.getTimeOfSessionDate()),
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
                .setHallId(cinemaHall.getHallId())
                .setName(cinemaHall.getName())
                .setSeatCount(cinemaHall.getSeatCount())
                .build();
    }
}
