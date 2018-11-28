package com.brekhin.gateway.service;

import com.brekhin.gateway.web.to.in.AddMovieRequest;

import java.util.UUID;

public interface MovieService {
    UUID addMovie(AddMovieRequest request);
}
