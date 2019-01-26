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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(path = "/api/ticket-api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TicketController {

    private final TicketService ticketService;
    private final MovieSessionService movieSessionService;

    @Autowired
    public TicketController(TicketService ticketService, MovieSessionService movieSessionService) {
        this.ticketService = ticketService;
        this.movieSessionService = movieSessionService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TicketTarget> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping(path = "/{sessionId}/selectseat")
    public String getAllSeats(@PathVariable Long sessionId, Model model) {
        TicketBuildStep1 ticketInfo = movieSessionService.getInfoTimeOfSessionById(sessionId);
        model.addAttribute("movieInfo", ticketInfo);
        return "selectSeat";
    }


    @PostMapping(path = "/{sessionId}/selectseat")
    public String setSeat(
            @PathVariable Long sessionId,
            @RequestParam(value = "row") String row,
            @RequestParam(value = "col") String col,
            Model model) {

        TicketBuildStep1 ticketBuildStep1 = movieSessionService.getInfoTimeOfSessionById(sessionId);
        TicketBuildStep2 ticketBuildStep2 = new TicketBuildStep2(ticketBuildStep1, Integer.parseInt(row), Integer.parseInt(col));
        Long ticketId = ticketService.addTicket(ticketBuildStep2);

        return "redirect:/api/ticket-api/order/" + ticketId;
    }

    @GetMapping(path = "/order/{ticketId}")
    public String getInfoAboutTicket(@PathVariable Long ticketId, Model model) {
        TicketTarget ticket = ticketService.getTicketById(ticketId);
        model.addAttribute("ticket", ticket);
        return "ticketInfo";
    }
}
