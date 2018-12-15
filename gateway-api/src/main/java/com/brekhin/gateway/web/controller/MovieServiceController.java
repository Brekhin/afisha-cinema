package com.brekhin.gateway.web.controller;

import com.brekhin.gateway.service.MovieSessionService;
import com.brekhin.gateway.web.to.in.moviesession.AddDateOfSessionRequest;
import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.out.moviesession.AddDateOfSessionResponse;
import com.brekhin.gateway.web.to.out.moviesession.AddTimeOfSessionResponse;
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

    @RequestMapping(path = "/newdate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AddDateOfSessionResponse> addDateOfSession(@RequestBody @Valid AddDateOfSessionRequest request) {
        return ResponseEntity.ok(new AddDateOfSessionResponse(movieSessionService.addDateOfSession(request)));
    }

    @RequestMapping(path = "/newdate", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AddDateOfSessionRequest>> getAllDateOfSession() {
        return ResponseEntity.ok(movieSessionService.getAllDateOfSession());
    }

    @RequestMapping(path = "/newsessoin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AddTimeOfSessionResponse> addTimeOfSession(@RequestBody @Valid AddTimeOfSessionRequest request) {
        return ResponseEntity.ok(new AddTimeOfSessionResponse(movieSessionService.addTimeOfSession(request)));
    }
}
