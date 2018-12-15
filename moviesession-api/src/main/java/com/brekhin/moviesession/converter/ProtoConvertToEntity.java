package com.brekhin.moviesession.converter;

import com.brekhin.moviesession.entity.DateOfSessionEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import com.brekhin.moviesession.grpc.model.GDateOfSession;
import com.brekhin.moviesession.grpc.model.GTimeOfSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.stream.Collectors;

public class ProtoConvertToEntity {

    private final static Logger log = LoggerFactory.getLogger(ProtoConvertToEntity.class);

    public static DateOfSessionEntity convert(GDateOfSession gDateOfSession) {
        return new DateOfSessionEntity()
                .setDateOfSessionId(gDateOfSession.getDateOfSessionId())
                .setDateOfSession(new Timestamp(gDateOfSession.getDateOfSession()))
                .setTimeOfSession(gDateOfSession.getTimeOfSessionList().stream()
                        .map(ProtoConvertToEntity::convert)
                        .collect(Collectors.toSet()));
    }

    public static GDateOfSession convert(DateOfSessionEntity gDateOfSession) {
        return GDateOfSession.newBuilder()
                .setDateOfSessionId(gDateOfSession.getDateOfSessionId())
                .setDateOfSession(gDateOfSession.getDateOfSession().getTime())
                .addAllTimeOfSession(gDateOfSession.getTimeOfSession().stream()
                        .map(ProtoConvertToEntity::convert)
                        .collect(Collectors.toList()))
                .build();
    }

    public static TimeOfSessionEntity convert(GTimeOfSession gTimeOfSession) {
        return new TimeOfSessionEntity()
                .setTimeOfSessionId(gTimeOfSession.getTimeOfSessionId())
                .setTimeOfSessionDate(new Timestamp(gTimeOfSession.getTimeOfSessionDate()))
                .setMovieId(gTimeOfSession.getMovieId());
    }

    public static GTimeOfSession convert(TimeOfSessionEntity gTimeOfSession) {
        return GTimeOfSession.newBuilder()
                .setTimeOfSessionId(gTimeOfSession.getTimeOfSessionId())
                .setTimeOfSessionDate(gTimeOfSession.getTimeOfSession().getTime())
                .setMovieId(gTimeOfSession.getMovieId())
                .build();
    }

}
