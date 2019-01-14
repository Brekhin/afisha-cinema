package com.brekhin.moviesession.converter;

import com.brekhin.moviesession.entity.CinemaHallEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import com.brekhin.moviesession.grpc.model.GCinemaHall;
import com.brekhin.moviesession.grpc.model.GTimeOfSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class ProtoConvertToEntity {

    public static TimeOfSessionEntity convert(GTimeOfSession gTimeOfSession) {
        return new TimeOfSessionEntity()
                .setTimeOfSessionId(gTimeOfSession.getTimeOfSessionId())
                .setTimeOfSessionDate(new Timestamp(gTimeOfSession.getTimeOfSessionDate()))
                .setMovieId(gTimeOfSession.getMovieId())
                .setPrice(gTimeOfSession.getPrice())
                .setHallId(gTimeOfSession.getHallId());
    }

    public static GTimeOfSession convert(TimeOfSessionEntity gTimeOfSession) {
        return GTimeOfSession.newBuilder()
                .setTimeOfSessionId(gTimeOfSession.getTimeOfSessionId())
                .setTimeOfSessionDate(gTimeOfSession.getTimeOfSessionDate().getTime())
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
                .setHallId(gCinemaHall.getHallId())
                .setName(gCinemaHall.getName())
                .setSeatCount(gCinemaHall.getSeatCount());
    }
}
