package com.brekhin.movie.service;

import com.brekhin.movie.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MovieService {

    Page<MovieEntity> getAllMovies(Pageable pageable);

    Long addMovie(MovieEntity movieEntity);

    MovieEntity getMovie(Long movieId);

    void removeMovie(Long movieId);
}
