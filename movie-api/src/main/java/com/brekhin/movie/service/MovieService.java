package com.brekhin.movie.service;

import com.brekhin.movie.entity.MovieEntity;

import java.util.List;
import java.util.UUID;

public interface MovieService {

    List<MovieEntity> getAllMovies();

    UUID addMovie(MovieEntity movieEntity);

    MovieEntity getMovie(UUID movieId);

    void removeMovie(UUID movieId);
}
