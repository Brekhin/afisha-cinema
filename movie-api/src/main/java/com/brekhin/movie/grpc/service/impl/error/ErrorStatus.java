package com.brekhin.movie.grpc.service.impl.error;

import com.brekhin.movie.exception.NotFoundMovieException;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class ErrorStatus {

    public static void status(StreamObserver<?> responseObserver, Exception e) {
        Status status;
        if (e instanceof IllegalArgumentException) {
            status = Status.INVALID_ARGUMENT;
        } else if (e instanceof NotFoundMovieException) {
            status = Status.NOT_FOUND;
        } else {
            status = Status.INTERNAL;
        }
    }
}
