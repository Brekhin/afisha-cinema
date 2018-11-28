package com.brekhin.gateway.service.impl;

import com.brekhin.gateway.converter.CommonConverter;
import com.brekhin.gateway.converter.MovieConverter;
import com.brekhin.gateway.exception.util.GRPCExceptionUtil;
import com.brekhin.gateway.grpc.client.GRpcMovieServiceClient;
import com.brekhin.gateway.service.MovieService;
import com.brekhin.gateway.web.to.in.AddMovieRequest;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final GRpcMovieServiceClient rpcMovieServiceClient;

    @Autowired
    public MovieServiceImpl(GRpcMovieServiceClient rpcMovieServiceClient) {
        this.rpcMovieServiceClient = rpcMovieServiceClient;
    }


    @Override
    public UUID addMovie(AddMovieRequest request) {
        try {
            UUID convert = CommonConverter.convert(rpcMovieServiceClient.addMovie(
                    MovieConverter.convert(request)).
                    getMovieId());
            return convert;
        } catch (StatusRuntimeException e) {
            log.error("Failed to add movie with name: {}", request.getName());
            throw GRPCExceptionUtil.movieModuleException(e);
        }
    }
}