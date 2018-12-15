package com.brekhin.gateway.web.controller;

import com.brekhin.gateway.service.MovieService;
import com.brekhin.gateway.web.to.in.movie.AddMovieRequest;
import com.brekhin.gateway.web.to.in.movie.DeleteMovieRequest;
import com.brekhin.gateway.web.to.out.movie.AddMovie;
import com.brekhin.gateway.web.to.out.movie.GetMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/movies", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AddMovie> addMovie(@RequestBody @Valid AddMovieRequest request) {
        return ResponseEntity.ok(new AddMovie(movieService.addMovie(request)));
    }

    @RequestMapping(path = "/{movieId}", method = RequestMethod.GET)
    public ResponseEntity<GetMovie> getMovie(@PathVariable Long movieId){
        return ResponseEntity.ok(movieService.getMovie(movieId));
    }

    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> removeMovie(@RequestBody @Valid DeleteMovieRequest request){
        movieService.removeMovieById(request.getMovieId());
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<GetMovie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

}
