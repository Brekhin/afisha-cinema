package com.brekhin.movie.grpc.service.impl;

import com.brekhin.movie.grpc.Empty;
import com.brekhin.movie.grpc.model.*;
import com.brekhin.movie.grpc.service.MovieServiceGrpc;
import com.brekhin.movie.service.impl.MovieServiceImpl;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class MovieServiceGrpcImpl extends MovieServiceGrpc.MovieServiceImplBase {

    @Autowired
    private MovieServiceImpl movieService;

    @Override
    public void getAllMovies(Empty request, StreamObserver<gRPCGetAllMoviesResponse> responseObserver) {
        super.getAllMovies(request, responseObserver);
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
