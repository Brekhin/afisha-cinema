package com.brekhin.gateway.web.controller;

import com.brekhin.gateway.service.MovieService;
import com.brekhin.gateway.service.MovieSessionService;
import com.brekhin.gateway.web.to.in.movie.AddMovieRequest;
import com.brekhin.gateway.web.to.in.movie.DeleteMovieRequest;
import com.brekhin.gateway.web.to.out.movie.AddMovie;
import com.brekhin.gateway.web.to.out.movie.GetMovie;
import com.brekhin.gateway.web.to.out.moviesession.InfoTimeOfSessionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieController {

    Logger log = LoggerFactory.getLogger(MovieController.class);

    private final MovieService movieService;
    private final MovieSessionService movieSessionService;

    @Autowired
    public MovieController(MovieService movieService, MovieSessionService movieSessionService) {
        this.movieService = movieService;
        this.movieSessionService = movieSessionService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AddMovie> addMovie(@RequestBody @Valid AddMovieRequest request) {
        return ResponseEntity.ok(new AddMovie(movieService.addMovie(request)));
    }


    @RequestMapping(path = "/{movieId}", method = RequestMethod.GET)
    public ResponseEntity<GetMovie> getMovie(@PathVariable Long movieId){
        List<InfoTimeOfSessionResponse> sessionsByMovieId = movieSessionService.getSessionsByMovieId(movieId);
        GetMovie movie = movieService.getMovie(movieId);
        movie.getSessions().addAll(sessionsByMovieId);
        return ResponseEntity.ok(movie);
    }


    @RequestMapping(path = "/{movieId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> removeMovie(@PathVariable Long movieId){
        movieService.removeMovieById(movieId);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<GetMovie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

}
