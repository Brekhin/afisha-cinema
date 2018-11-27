package com.brekhin.movie.converter;

import com.brekhin.movie.entity.MovieEntity;
import com.brekhin.movie.grpc.GUuid;
import com.brekhin.movie.grpc.model.GMovie;

import java.util.UUID;

public class ProtoConvertToEntity {

    public static UUID convert(String uuid){
        return UUID.fromString(uuid);
    }

    public static GUuid convert(UUID uuid) {
        return GUuid.newBuilder()
                .setUuid(String.valueOf(uuid))
                .build();
    }

    public static UUID convert(GUuid guuid) {
        return UUID.fromString(guuid.getUuid());
    }

    public static MovieEntity convert(GMovie gMovie) {
        return new MovieEntity()
                .setMovieId(convert(gMovie.getUuid()))
                .setName(gMovie.getName())
                .setRentalStartDate(new java.sql.Timestamp(gMovie.getRentalStartDate()))
                .setRentalEndDate(new java.sql.Timestamp(gMovie.getRentalEndDate()))
                .setGenre(gMovie.getGenre())
                .setDuration(gMovie.getDuration());
    }

    public static GMovie convert(MovieEntity movieEntity){
        return GMovie.newBuilder()
                .setUuid(convert(movieEntity.getMovieId()))
                .setName(movieEntity.getName())
                .setRentalStartDate(movieEntity.getRentalStartDate().getTime())
                .setRentalEndDate(movieEntity.getRentalEndDate().getTime())
                .setGenre(movieEntity.getGenre())
                .setDuration(movieEntity.getDuration())
                .build();

    }


}
