package com.brekhin.moviesession.converter;

import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import com.brekhin.moviesession.grpc.model.GTimeOfSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

public class ProtoConvertToEntity {
    static Logger log = LoggerFactory.getLogger(ProtoConvertToEntity.class);

    public static TimeOfSessionEntity convert(GTimeOfSession gTimeOfSession) {
        log.error(Integer.toString(gTimeOfSession.getPrice()));
        return new TimeOfSessionEntity()
                .setTimeOfSessionId(gTimeOfSession.getTimeOfSessionId())
                .setTimeOfSessionDate(new Timestamp(gTimeOfSession.getTimeOfSessionDate()))
                .setMovieId(gTimeOfSession.getMovieId())
                .setPrice(gTimeOfSession.getPrice());
    }

    public static GTimeOfSession convert(TimeOfSessionEntity gTimeOfSession) {
        return GTimeOfSession.newBuilder()
                .setTimeOfSessionId(gTimeOfSession.getTimeOfSessionId())
                .setTimeOfSessionDate(gTimeOfSession.getTimeOfSessionDate().getTime())
                .setMovieId(gTimeOfSession.getMovieId())
                .setPrice(gTimeOfSession.getPrice())
                .build();
    }

}
