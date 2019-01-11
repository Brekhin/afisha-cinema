package com.brekhin.gateway.web.controller;

import com.brekhin.gateway.service.MovieService;
import com.brekhin.gateway.service.MovieSessionService;
import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.out.moviesession.AddTimeOfSession;
import com.brekhin.gateway.web.to.out.moviesession.InfoTimeOfSessionResponse;
import com.brekhin.movie.grpc.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/movieservice-api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MovieServiceController {

    private final MovieSessionService movieSessionService;

    @Autowired
    public MovieServiceController(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @RequestMapping(path = "/session", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> addTimeOfSession(@RequestBody @Valid AddTimeOfSessionRequest request) {
        Long id = new AddTimeOfSession(movieSessionService.addTimeOfSession(request)).getTimeOfSessionId();
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/session/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteSessionById(@PathVariable Long id) {
        movieSessionService.deleteSessionById(id);
        return ResponseEntity.noContent().build();
    }
}