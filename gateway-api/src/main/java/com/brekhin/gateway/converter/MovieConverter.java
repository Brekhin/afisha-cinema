package com.brekhin.gateway.converter;

import com.brekhin.gateway.web.to.in.movie.AddMovieRequest;
import com.brekhin.gateway.web.to.out.movie.GetMovie;
import com.brekhin.movie.grpc.model.GMovie;
import com.brekhin.movie.grpc.model.gRPCAddMovieRequest;

import java.sql.Timestamp;

public class MovieConverter {

    public static gRPCAddMovieRequest convert(AddMovieRequest request) {
        return gRPCAddMovieRequest.newBuilder()
                .setMovie(GMovie.newBuilder()
                        .setMovieId(request.getMovieId())
                        .setName(request.getName())
                        .setRentalStartDate(request.getRentalStartDate().getTime())
                        .setRentalEndDate(request.getRentalEndDate().getTime())
                        .setGenre(request.getGenre())
                        .setDuration(request.getDuration())
                        .build())
                .build();

    }

    public static GetMovie convert(GMovie movie) {
        return new GetMovie(movie.getMovieId(),
                movie.getName(),
                new Timestamp(movie.getRentalStartDate()),
                new Timestamp(movie.getRentalEndDate()),
                movie.getGenre(),
                movie.getDuration());
    }
}
