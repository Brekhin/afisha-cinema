package com.brekhin.movie.converter;

import com.brekhin.movie.entity.MovieEntity;
import com.brekhin.movie.grpc.model.GMovie;
import com.google.protobuf.Timestamp;

import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

public class ProtoConvertToEntity {

    public static UUID convert(String uuid){
        return UUID.fromString(uuid);
    }

    public static MovieEntity convert(GMovie gMovie) {
        return new MovieEntity()
                .setMovieId(convert(gMovie.getUuid()))
                .setName(gMovie.getName())
                .setRentalStartDate(new Date(Long.getLong(gMovie.getRentalStartDate().toString())))
                .setRentalEndDate(new Date(Long.getLong(gMovie.getRentalEndDate().toString())))
                .setGenre(gMovie.getGenre())
                .setDuration(gMovie.getDuration());
    }

    public static GMovie convert(MovieEntity movieEntity){
        final Instant instantStart = java.sql.Timestamp.valueOf(movieEntity.getRentalStartDate().toString()).toInstant();
        final Instant instantEnd = java.sql.Timestamp.valueOf(movieEntity.getRentalEndDate().toString()).toInstant();

        return GMovie.newBuilder()
                .setUuid(movieEntity.getMovieId().toString())
                .setName(movieEntity.getName())
                .setRentalStartDate(Timestamp.newBuilder()
                        .setSeconds(instantStart.getEpochSecond())
                        .build())
                .setRentalEndDate(Timestamp.newBuilder().
                        setSeconds(instantEnd.getEpochSecond())
                        .build())
                .setGenre(movieEntity.getGenre())
                .setDuration(movieEntity.getDuration())
                .build();

    }


}
