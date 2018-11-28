package com.brekhin.movie.converter;

import com.brekhin.movie.entity.MovieEntity;
import com.brekhin.movie.grpc.GUuid;
import com.brekhin.movie.grpc.model.GMovie;

public class ProtoConvertToEntity {

    public static GUuid convert(Long uuid) {
        return GUuid.newBuilder()
                .setUuid(String.valueOf(uuid))
                .build();
    }

    public static MovieEntity convert(GMovie gMovie) {
        return new MovieEntity()
                .setMovieId(gMovie.getMovieId())
                .setName(gMovie.getName())
                .setRentalStartDate(new java.sql.Timestamp(gMovie.getRentalStartDate()))
                .setRentalEndDate(new java.sql.Timestamp(gMovie.getRentalEndDate()))
                .setGenre(gMovie.getGenre())
                .setDuration(gMovie.getDuration());
    }

    public static GMovie convert(MovieEntity movieEntity){
        return GMovie.newBuilder()
                .setMovieId(movieEntity.getMovieId())
                .setName(movieEntity.getName())
                .setRentalStartDate(movieEntity.getRentalStartDate().getTime())
                .setRentalEndDate(movieEntity.getRentalEndDate().getTime())
                .setGenre(movieEntity.getGenre())
                .setDuration(movieEntity.getDuration())
                .build();

    }


}
