package com.brekhin.gateway.service;

import com.brekhin.gateway.web.to.in.AddMovieRequest;
import com.brekhin.gateway.web.to.out.GetMovie;

import java.util.UUID;

public interface MovieService {
    Long addMovie(AddMovieRequest request);

    GetMovie getMovie(Long movieId);

    void removeMovieById(Long movieId);
}
