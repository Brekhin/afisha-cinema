package com.brekhin.movie.converter;

import com.brekhin.movie.entity.MovieEntity;
import com.brekhin.movie.grpc.GUuid;
import com.brekhin.movie.grpc.model.GMovie;
import com.brekhin.movie.grpc.model.GPageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ProtoConvertToEntity {

    public static Pageable convert(GPageable gPageable){
        return PageRequest.of(gPageable.getPage(), gPageable.getSize());
    }

    public static MovieEntity convert(GMovie gMovie) {
        return new MovieEntity()
                .setMovieId(gMovie.getMovieId())
                .setName(gMovie.getName())
                .setGenre(gMovie.getGenre())
                .setDuration(gMovie.getDuration());
    }

    public static GMovie convert(MovieEntity movieEntity){
        return GMovie.newBuilder()
                .setMovieId(movieEntity.getMovieId())
                .setName(movieEntity.getName())
                .setGenre(movieEntity.getGenre())
                .setDuration(movieEntity.getDuration())
                .build();

    }


}
