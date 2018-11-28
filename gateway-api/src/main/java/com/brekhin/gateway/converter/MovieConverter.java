package com.brekhin.gateway.converter;

import com.brekhin.gateway.web.to.in.AddMovieRequest;
import com.brekhin.movie.grpc.model.GMovie;
import com.brekhin.movie.grpc.model.gRPCAddMovieRequest;

public class MovieConverter {

    public static gRPCAddMovieRequest convert(AddMovieRequest request) {
        return gRPCAddMovieRequest.newBuilder()
                .setMovie(GMovie.newBuilder()
                        .setUuid(CommonConverter.convert(request.getMovieId()))
                        .setName(request.getName())
                        .setRentalStartDate(request.getRentalStartDate().getTime())
                        .setRentalEndDate(request.getRentalEndDate().getTime())
                        .setGenre(request.getGenre())
                        .setDuration(request.getDuration())
                        .build())
                .build();

    }
}
