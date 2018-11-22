package com.brekhin.movie.grpc.service.impl;

import com.brekhin.movie.converter.ProtoConvertToEntity;
import com.brekhin.movie.entity.MovieEntity;
import com.brekhin.movie.exception.NotFoundMovieException;
import com.brekhin.movie.grpc.Empty;
import com.brekhin.movie.grpc.model.*;
import com.brekhin.movie.grpc.service.MovieServiceGrpc;
import com.brekhin.movie.service.impl.MovieServiceImpl;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GRpcService
public class MovieServiceGrpcImpl extends MovieServiceGrpc.MovieServiceImplBase {

    @Autowired
    private MovieServiceImpl movieService;

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
            error(responseObserver, e);
        }
    }

    private void error(StreamObserver<gRPCGetAllMoviesResponse> responseObserver, Exception e) {
        Status status;
        if (e instanceof IllegalArgumentException) {
            status = Status.INVALID_ARGUMENT;
        } else if (e instanceof NotFoundMovieException) {
            status = Status.NOT_FOUND;
        } else {
            status = Status.INTERNAL;
        }
    }

    @Override
    public void addMovie(gRPCAddMovieRequest request, StreamObserver<gRPCAddMovieResponse> responseObserver) {
        super.addMovie(request, responseObserver);
    }

    @Override
    public void getMovie(gRPCGetMovieRequest request, StreamObserver<gRPCGetMovieResponse> responseObserver) {
        super.getMovie(request, responseObserver);
    }

    @Override
    public void removeMovie(gRPCRemoveMovieRequest request, StreamObserver<Empty> responseObserver) {
        super.removeMovie(request, responseObserver);
    }


}
