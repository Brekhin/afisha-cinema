package com.brekhin.moviesession.grpc.service.impl;

import com.brekhin.moviesession.grpc.service.MovieSessionServiceGrpc;
import com.brekhin.moviesession.service.CinemaSessionService;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class MovieSessionServiceGrpcImpl extends MovieSessionServiceGrpc.MovieSessionServiceImplBase {

    private final CinemaSessionService cinemaSessionService;

    @Autowired
    public MovieSessionServiceGrpcImpl(CinemaSessionService cinemaSessionService) {
        this.cinemaSessionService = cinemaSessionService;
    }
}
