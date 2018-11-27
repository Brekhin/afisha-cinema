package com.brekhin.movie.grpc.service.impl;

import com.brekhin.movie.converter.ProtoConvertToEntity;
import com.brekhin.movie.entity.MovieEntity;
import com.brekhin.movie.exception.NotFoundMovieException;
import com.brekhin.movie.grpc.Empty;
import com.brekhin.movie.grpc.model.*;
import com.brekhin.movie.grpc.service.MovieServiceGrpc;
import com.brekhin.movie.grpc.service.impl.error.ErrorStatus;
import com.brekhin.movie.service.MovieService;
import com.brekhin.movie.service.impl.MovieServiceImpl;
import io.grpc.Status;
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

    static final Logger log = LoggerFactory.getLogger(MovieServiceGrpcImpl.class);


    private final MovieService movieService;

    @Autowired
    public MovieServiceGrpcImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void getAllMovies(Empty request, StreamObserver<gRPCGetAllMoviesResponse> responseObserver) {
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
            UUID movieUID = movieService.addMovie(ProtoConvertToEntity.convert(request.getMovie()));
            responseObserver.onNext(gRPCAddMovieResponse.newBuilder()
                    .setMovieId(movieUID.toString())
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            ErrorStatus.status(responseObserver, e);
        }
    }

    @Override
    public void getMovie(gRPCGetMovieRequest request, StreamObserver<gRPCGetMovieResponse> responseObserver) {
        try {
            log.info("ID: {}", request.getMovieId().getUuid());
            log.info("convert: {}", ProtoConvertToEntity.convert(request.getMovieId()));
            MovieEntity movie = movieService.getMovie(ProtoConvertToEntity.convert(request.getMovieId()));
            log.info("Entity: {}", movie.toString());
            responseObserver.onNext(gRPCGetMovieResponse.newBuilder()
                    .setMovie(ProtoConvertToEntity.convert(movie))
                    .build());
            responseObserver.onCompleted();
            log.info("SUCCESSs: getMovie");
        } catch (Exception e) {
            log.error("ERROR: getMovie");
            ErrorStatus.status(responseObserver, e);
        }
    }

    @Override
    public void removeMovie(gRPCRemoveMovieRequest request, StreamObserver<Empty> responseObserver) {
        try {
            movieService.removeMovie(ProtoConvertToEntity.convert(request.getMovieId()));
            responseObserver.onNext(Empty.newBuilder().build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("ERROR: remove");
            ErrorStatus.status(responseObserver, e);
        }
    }


}
