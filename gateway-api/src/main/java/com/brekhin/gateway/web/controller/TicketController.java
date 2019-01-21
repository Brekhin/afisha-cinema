package com.brekhin.gateway.web.controller;

import com.brekhin.gateway.service.MovieService;
import com.brekhin.gateway.service.MovieSessionService;
import com.brekhin.gateway.service.TicketService;
import com.brekhin.gateway.web.to.in.ticket.TicketBuildStep1;;
import com.brekhin.gateway.web.to.in.ticket.TicketBuildStep2;
import com.brekhin.gateway.web.to.out.ticket.TicketTarget;
import com.brekhin.movie.grpc.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/ticket-api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TicketController {

    private final TicketService ticketService;
    private final MovieSessionService movieSessionService;

    @Autowired
    public TicketController(TicketService ticketService, MovieSessionService movieSessionService) {
        this.ticketService = ticketService;
        this.movieSessionService = movieSessionService;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> addTicket(@RequestBody TicketBuildStep2 ticketInfo) {
        ticketService.addTicket(ticketInfo);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TicketTarget> getTicketById(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PutMapping(path = "/{sessionId}/selectseat", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getSessionById(@PathVariable Long sessionId, Model model) {
        TicketBuildStep1 ticketInfo = movieSessionService.getInfoTimeOfSessionById(sessionId);
//        TicketBuildStep2 step2 = new TicketBuildStep2(
//                ticketBuild.getTicketId(),
//                ticketBuild.getTicketBuildStep1(),
//                ticketBuild.getCol(),
//                ticketBuild.getRow()));
        return null;
    }
}
