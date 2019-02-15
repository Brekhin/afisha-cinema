package com.brekhin.gateway.web.controller;

import com.brekhin.gateway.service.MovieService;
import com.brekhin.gateway.service.MovieSessionService;
import com.brekhin.gateway.web.to.in.movie.AddMovieRequest;
import com.brekhin.gateway.web.to.out.movie.GetMovie;
import com.brekhin.gateway.web.to.out.moviesession.InfoTimeOfSessionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieSessionService movieSessionService;

    @Autowired
    public MovieController(MovieService movieService, MovieSessionService movieSessionService) {
        this.movieService = movieService;
        this.movieSessionService = movieSessionService;
    }

    @PostMapping(path = "/newmovie", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Long> addMovie(@Valid @RequestBody AddMovieRequest request) {
        Long movieId = movieService.addMovie(request);
        return ResponseEntity.ok(movieId);
    }

    @GetMapping(path = "/newmovie")
    public String addMovieGetPage(){
        return "addMovie";
    }

    @GetMapping(path = "/{movieId}")
    public ResponseEntity<GetMovie> getMovie(@PathVariable Long movieId, Model model){
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

    @GetMapping
    public ResponseEntity<List<GetMovie>> getAllMovies(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        List<GetMovie> allMovies = movieService.getAllMovies(page, size);
        return ResponseEntity.ok(allMovies);
    }

}
