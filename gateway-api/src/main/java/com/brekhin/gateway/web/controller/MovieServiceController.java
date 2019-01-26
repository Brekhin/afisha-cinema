package com.brekhin.gateway.web.controller;

import com.brekhin.gateway.service.MovieService;
import com.brekhin.gateway.service.MovieSessionService;
import com.brekhin.gateway.service.TicketService;
import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.in.ticket.TicketBuildStep1;
import com.brekhin.gateway.web.to.out.moviesession.AddTimeOfSession;
import com.brekhin.gateway.web.to.out.moviesession.CinemaHallTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/api/movieservice-api")
public class MovieServiceController {

    private static Logger log = LoggerFactory.getLogger(MovieServiceController.class);
    private final MovieSessionService movieSessionService;
    private final TicketService ticketService;

    @Autowired
    public MovieServiceController(MovieSessionService movieSessionService,
                                  TicketService ticketService) {
        this.movieSessionService = movieSessionService;
        this.ticketService = ticketService;
    }

    @PostMapping(path = "/{movieId}/session")
    public String addSession(@PathVariable Long movieId, AddTimeOfSessionRequest request) {
        System.out.println("123");
        log.error(request.toString());
        Long id = new AddTimeOfSession(movieSessionService.addTimeOfSession(request)).getTimeOfSessionId();
        return "redirect:/api/movies/" + movieId;
    }

    @GetMapping(path = "/{movieId}/session")
    public String getPageForAddSession(@PathVariable Long movieId, Model model) {
        List<CinemaHallTO> allCinemaHall = movieSessionService.getAllCinemaHall();
        model.addAttribute("cinemaHalls", allCinemaHall);
        model.addAttribute("movieId", movieId);
        return "addSession";
    }

    @RequestMapping(path = "/session/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteSessionById(@PathVariable Long id) {
        movieSessionService.deleteSessionById(id);
        ticketService.deleteTicketsBySessionId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/session/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSessionById(@PathVariable Long id, Model model) {
        TicketBuildStep1 ticketInfo = movieSessionService.getInfoTimeOfSessionById(id);
        return "redirect:/gateway-api/api/ticket-api/selectseat";
    }

    @RequestMapping(path = "/session/hall", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Long> addNewHall(@RequestBody @Valid CinemaHallTO cinemaHallTO){
        return ResponseEntity.ok(movieSessionService.addCinemaHall(cinemaHallTO));
    }

    @RequestMapping(path = "/session/hall/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Long> deleteHallById(@PathVariable Long id){
        return ResponseEntity.ok(movieSessionService.deleteCinemaHallById(id));
    }

    @RequestMapping(path = "/session/hall", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CinemaHallTO>> getAllHalls(){
        return ResponseEntity.ok(movieSessionService.getAllCinemaHall());
    }

    @RequestMapping(path = "/session/hall/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CinemaHallTO> getCinemaHallById(@PathVariable Long id){
        return ResponseEntity.ok(movieSessionService.getCinemaHallById(id));
    }

}
