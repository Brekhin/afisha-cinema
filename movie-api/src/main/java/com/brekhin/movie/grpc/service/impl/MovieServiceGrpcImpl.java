package com.brekhin.movie.grpc.service.impl;

import com.brekhin.movie.converter.ProtoConvertToEntity;
import com.brekhin.movie.entity.MovieEntity;
import com.brekhin.movie.grpc.Empty;
import com.brekhin.movie.grpc.model.*;
import com.brekhin.movie.grpc.service.MovieServiceGrpc;
import com.brekhin.movie.grpc.service.impl.error.ErrorStatus;
import com.brekhin.movie.service.MovieService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GRpcService
public class MovieServiceGrpcImpl extends MovieServiceGrpc.MovieServiceImplBase {

    private final MovieService movieService;

    public static final Logger log = LoggerFactory.getLogger(MovieServiceGrpcImpl.class);

    @Autowired
    public MovieServiceGrpcImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void getAllMovies(gRPCGetAllMoviesRequest request, StreamObserver<gRPCGetAllMoviesResponse> responseObserver) {
        try {
            List<MovieEntity> allMovies = movieService.getAllMovies();
            responseObserver.onNext(gRPCGetAllMoviesResponse.newBuilder().
                    addAllMovies(allMovies
                            .stream()
                            .map(ProtoConvertToEntity::convert)
                            .collect(Collectors.toList()))
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            ErrorStatus.status(responseObserver, e);
        }
    }


    @Override
    public void addMovie(gRPCAddMovieRequest request, StreamObserver<gRPCAddMovieResponse> responseObserver) {
        try {
            Long movieId = movieService.addMovie(ProtoConvertToEntity.convert(request.getMovie()));
            responseObserver.onNext(gRPCAddMovieResponse.newBuilder()
                    .setMovieId(movieId)
                    .build());

            responseObserver.onCompleted();
        } catch (Exception e) {
            ErrorStatus.status(responseObserver, e);
        }
    }

    @Override
    public void getMovie(gRPCGetMovieRequest request, StreamObserver<gRPCGetMovieResponse> responseObserver) {
        try {
            MovieEntity movie = movieService.getMovie(request.getMovieId());

            responseObserver.onNext(gRPCGetMovieResponse.newBuilder()
                    .setMovie(ProtoConvertToEntity.convert(movie))
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            ErrorStatus.status(responseObserver, e);
        }
    }

    @Override
    public void removeMovie(gRPCRemoveMovieRequest request, StreamObserver<Empty> responseObserver) {
        try {
            movieService.removeMovie(request.getMovieId());
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            ErrorStatus.status(responseObserver, e);
        }
    }


}
