package com.brekhin.gateway.converter;

import com.brekhin.gateway.web.to.in.moviesession.AddDateOfSessionRequest;
import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.out.moviesession.AddDateOfSessionResponse;
import com.brekhin.gateway.web.to.out.moviesession.AddTimeOfSessionResponse;
import com.brekhin.moviesession.grpc.model.GDateOfSession;
import com.brekhin.moviesession.grpc.model.GTimeOfSession;
import com.brekhin.moviesession.grpc.model.gRPCAddDateOfSessionRequest;
import com.brekhin.moviesession.grpc.model.gRPCAddTimeOfSessionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.stream.Collectors;

public class MovieSessionConverter {
    private final static Logger log = LoggerFactory.getLogger(MovieSessionConverter.class);

    public static gRPCAddDateOfSessionRequest convert(AddDateOfSessionRequest request) {
        log.warn("This is sparta!! " +Integer.toString(request.getTimeOfSession().size()));
        return gRPCAddDateOfSessionRequest.newBuilder().
                setDateOfSession(GDateOfSession.newBuilder()
                        .setDateOfSessionId(request.getDateOfSessionId())
                        .setDateOfSession(request.getDateOfSession().getTime())
                        .addAllTimeOfSession(request.getTimeOfSession().stream()
                                .map(e -> GTimeOfSession.newBuilder()
                                        .setTimeOfSessionId(e.getTimeOfSessionId())
                                        .setTimeOfSessionDate(e.getTimeOfSessionDate().getTime())
                                        .setMovieId(e.getMovieId())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .build();
    }

    public static AddDateOfSessionRequest convert(GDateOfSession gDateOfSession) {
        log.warn("This is sparta!! " +Long.toString(gDateOfSession.getDateOfSessionId()));
        return new AddDateOfSessionRequest(
                gDateOfSession.getDateOfSessionId(),
                new Timestamp(gDateOfSession.getDateOfSession()),
                gDateOfSession.getTimeOfSessionList().stream()
                        .map(e -> new AddTimeOfSessionRequest(e.getTimeOfSessionId(),
                                new Timestamp(e.getTimeOfSessionDate()),
                                e.getMovieId()))
                        .collect(Collectors.toSet()));
    }

    public static gRPCAddTimeOfSessionRequest convert(AddTimeOfSessionRequest gTimeOfSession) {
     //   log.warn("This is sparta2!! " +Long.toString(gTimeOfSession.getDateOfSession().getDateOfSessionId()));
        return gRPCAddTimeOfSessionRequest.newBuilder()
                .setTimeOfSession(GTimeOfSession.newBuilder()
                        .setTimeOfSessionId(gTimeOfSession.getTimeOfSessionId())
                        .setTimeOfSessionDate(gTimeOfSession.getTimeOfSessionDate().getTime())
                        .setMovieId(gTimeOfSession.getMovieId())
                        .build())
                .build();
    }
}
