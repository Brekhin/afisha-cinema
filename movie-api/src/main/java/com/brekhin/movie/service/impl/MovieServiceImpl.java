package com.brekhin.movie.service.impl;

import com.brekhin.movie.entity.MovieEntity;
import com.brekhin.movie.exception.NotFoundMovieException;
import com.brekhin.movie.repository.MovieRepository;
import com.brekhin.movie.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class MovieServiceImpl implements MovieService {

    private static Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public UUID addMovie(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity).getMovieId();
    }

    @Override
    public MovieEntity getMovie(UUID movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> {
                    LOGGER.warn("Not found movie with id = {}", movieId);
                    return new NotFoundMovieException("Not found movie with id =" + movieId);
                });
    }

    @Override
    public void removeMovie(UUID movieId) {
        LOGGER.info("delete movie with id = {}", movieId);
        movieRepository.deleteById(movieId);
    }
}
