package com.brekhin.gateway.service;

import com.brekhin.gateway.web.to.in.movie.AddMovieRequest;
import com.brekhin.gateway.web.to.out.movie.GetMovie;

import java.util.List;

public interface MovieService {
    Long addMovie(AddMovieRequest request);

    GetMovie getMovie(Long movieId);

    void removeMovieById(Long movieId);

    List<GetMovie> getAllMovies();
}
