package com.brekhin.movie.service;

import com.brekhin.movie.entity.MovieEntity;

import java.util.List;
import java.util.UUID;

public interface MovieService {

    List<MovieEntity> getAllMovies();

    Long addMovie(MovieEntity movieEntity);

    MovieEntity getMovie(Long movieId);

    void removeMovie(Long movieId);
}
