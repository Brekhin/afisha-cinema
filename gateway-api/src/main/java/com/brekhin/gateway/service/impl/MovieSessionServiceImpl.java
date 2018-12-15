package com.brekhin.gateway.service.impl;

import com.brekhin.gateway.converter.MovieSessionConverter;
import com.brekhin.gateway.grpc.client.GRpcMovieSessionServiceClient;
import com.brekhin.gateway.service.MovieSessionService;
import com.brekhin.gateway.web.to.in.moviesession.AddDateOfSessionRequest;
import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.out.moviesession.AddDateOfSessionResponse;
import com.brekhin.gateway.web.to.out.moviesession.AddTimeOfSessionResponse;
import com.brekhin.moviesession.grpc.model.gRPCGetAllDateOfSessionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {

    private final static Logger log = LoggerFactory.getLogger(MovieSessionServiceImpl.class);
    private final GRpcMovieSessionServiceClient gRpcMovieSessionServiceClient;

    @Autowired
    public MovieSessionServiceImpl(GRpcMovieSessionServiceClient gRpcMovieSessionServiceClient) {
        this.gRpcMovieSessionServiceClient = gRpcMovieSessionServiceClient;
    }

    @Override
    public Long addDateOfSession(AddDateOfSessionRequest request) {
        MovieSessionConverter.convert(request).getDateOfSession().getTimeOfSessionList().stream()
        .forEach(e->log.warn(e.toString()));

        return gRpcMovieSessionServiceClient.addDateOfSession(MovieSessionConverter.convert(request)).getDateOfSessionId();
    }

    @Override
    public List<AddDateOfSessionRequest> getAllDateOfSession() {
        return gRpcMovieSessionServiceClient.getAllDateOfSession(gRPCGetAllDateOfSessionRequest.newBuilder().build())
                .getDateOfSessionList().stream()
                .map(MovieSessionConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Long addTimeOfSession(AddTimeOfSessionRequest request) {
        return gRpcMovieSessionServiceClient.addTimeOfSession(MovieSessionConverter.convert(request)).getTimeOfSessionId();
    }

    @Override
    public AddDateOfSessionResponse getAllSessionByDate(Long dateOfSessionId) {
        return null;
    }

    @Override
    public AddTimeOfSessionResponse getInfoAboutTimeOfSessionInSpecificDay(Long timeOfSessionId, Long dateOfSessionId) {
        return null;
    }

    @Override
    public void assigneTimeOfSessionsWithDay(Long timeOfSessionId, Long dateOfSessionId) {

    }

    @Override
    public void assignMovieWithSession(Long timeOfSessionId, Long movieId) {

    }
}
