package com.brekhin.moviesession.converter;

import com.brekhin.moviesession.entity.DateOfSessionEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import com.brekhin.moviesession.grpc.model.GDateOfSession;
import com.brekhin.moviesession.grpc.model.GTimeOfSession;

import java.sql.Timestamp;
import java.util.stream.Collectors;

public class ProtoConvertToEntity {

    public static DateOfSessionEntity convert(GDateOfSession gDateOfSession) {
        return new DateOfSessionEntity()
                .setDateOfSessionId(gDateOfSession.getDateOfSessionId())
                .setDateOfSession(new Timestamp(gDateOfSession.getDateOfSession()));
    }

    public static GDateOfSession convert(DateOfSessionEntity gDateOfSession) {
        return GDateOfSession.newBuilder()
                .setDateOfSessionId(gDateOfSession.getDateOfSessionId())
                .setDateOfSession(gDateOfSession.getDateOfSession().getTime())
                .addAllTimeOfSession(gDateOfSession.getTimeOfSessionEntitie().stream()
                        .map(ProtoConvertToEntity::convert)
                        .collect(Collectors.toList()))
                .build();
    }

    public static TimeOfSessionEntity convert(GTimeOfSession gTimeOfSession) {
        return new TimeOfSessionEntity()
                .setTimeOfSessionId(gTimeOfSession.getTimeOfSessionId())
                .setTimeOfSessionDate(new Timestamp(gTimeOfSession.getTimeOfSessionDate()))
                .setDateOfSession(ProtoConvertToEntity.convert(gTimeOfSession.getDateOfSessionId()))
                .setMovieId(gTimeOfSession.getMovieIds());
    }

    public static GTimeOfSession convert(TimeOfSessionEntity gTimeOfSession) {
        return GTimeOfSession.newBuilder()
                .setTimeOfSessionId(gTimeOfSession.getTimeOfSessionId())
                .setTimeOfSessionDate(gTimeOfSession.getTimeOfSession().getTime())
                .setDateOfSessionId(ProtoConvertToEntity.convert(gTimeOfSession.getDateOfSession()))
                .setMovieIds(gTimeOfSession.getMovieId())
                .build();
    }

}
