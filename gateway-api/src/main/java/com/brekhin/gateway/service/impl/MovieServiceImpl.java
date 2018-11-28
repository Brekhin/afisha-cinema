package com.brekhin.gateway.service.impl;

import com.brekhin.gateway.converter.MovieConverter;
import com.brekhin.gateway.exception.util.GRPCExceptionUtil;
import com.brekhin.gateway.grpc.client.GRpcMovieServiceClient;
import com.brekhin.gateway.service.MovieService;
import com.brekhin.gateway.web.to.in.AddMovieRequest;
import com.brekhin.gateway.web.to.out.GetMovie;
import com.brekhin.movie.grpc.model.GMovie;
import com.brekhin.movie.grpc.model.gRPCGetAllMoviesRequest;
import com.brekhin.movie.grpc.model.gRPCGetMovieRequest;
import com.brekhin.movie.grpc.model.gRPCRemoveMovieRequest;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final GRpcMovieServiceClient rpcMovieServiceClient;

    @Autowired
    public MovieServiceImpl(GRpcMovieServiceClient rpcMovieServiceClient) {
        this.rpcMovieServiceClient = rpcMovieServiceClient;
    }


    @Override
    public Long addMovie(AddMovieRequest request) {
        try {
            Long convert = rpcMovieServiceClient.addMovie(MovieConverter.convert(request)).getMovieId();

            return convert;
        } catch (StatusRuntimeException e) {
            log.error("Failed to add movie with name: {}", request.getName());
            throw GRPCExceptionUtil.movieModuleException(e);
        }
    }

    @Override
    public GetMovie getMovie(Long movieId) {
        return MovieConverter.convert(rpcMovieServiceClient.getMovie(gRPCGetMovieRequest.newBuilder()
                    .setMovieId(movieId)
                    .build())
                .getMovie()
        );
    }

    @Override
    public void removeMovieById(Long movieId) {
        rpcMovieServiceClient.removeMovie(gRPCRemoveMovieRequest.newBuilder()
                .setMovieId(movieId)
                .build());
    }

    @Override
    public List<GetMovie> getAllMovies() {
        return rpcMovieServiceClient.getAllMovies(gRPCGetAllMoviesRequest.newBuilder().build())
                .getMoviesList()
                    .stream()
                    .map(MovieConverter::convert)
                    .collect(Collectors.toList());
    }
}
