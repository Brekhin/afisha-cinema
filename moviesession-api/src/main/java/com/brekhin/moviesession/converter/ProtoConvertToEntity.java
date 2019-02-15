package com.brekhin.moviesession.converter;

import com.brekhin.moviesession.entity.CinemaHallEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import com.brekhin.moviesession.grpc.model.GCinemaHall;
import com.brekhin.moviesession.grpc.model.GSession;

public class ProtoConvertToEntity {

    public static TimeOfSessionEntity convert(GSession gSession) {
        return new TimeOfSessionEntity()
                .setTimeOfSession(gSession.getTimeOfSession())
                .setMovieId(gSession.getMovieId())
                .setPrice(gSession.getPrice())
                .setHallId(gSession.getHallId());
    }

    public static GSession convert(TimeOfSessionEntity gTimeOfSession) {
        return GSession.newBuilder()
                .setSessionId(gTimeOfSession.getTimeOfSessionId())
                .setTimeOfSession(gTimeOfSession.getTimeOfSession())
                .setMovieId(gTimeOfSession.getMovieId())
                .setPrice(gTimeOfSession.getPrice())
                .setHallId(gTimeOfSession.getHallId())
                .build();
    }

    public static GCinemaHall convert(CinemaHallEntity cinemaHallEntity) {
        return GCinemaHall.newBuilder()
                .setHallId(cinemaHallEntity.getHallId())
                .setName(cinemaHallEntity.getName())
                .setSeatCount(cinemaHallEntity.getSeatCount())
                .build();
    }

    public static CinemaHallEntity convert(GCinemaHall gCinemaHall) {
        return new CinemaHallEntity()
                .setName(gCinemaHall.getName())
                .setSeatCount(gCinemaHall.getSeatCount());
    }
}
