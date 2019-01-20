package com.brekhin.gateway.service;

import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.in.ticket.TicketBuildStep1;
import com.brekhin.gateway.web.to.out.moviesession.CinemaHallTO;
import com.brekhin.gateway.web.to.out.moviesession.InfoTimeOfSessionResponse;

import java.util.List;

public interface MovieSessionService {

    Long addTimeOfSession(AddTimeOfSessionRequest request);

    List<InfoTimeOfSessionResponse> getSessionsByMovieId(Long movieId);

    TicketBuildStep1 getInfoTimeOfSessionById(Long timeOfSessionId);

    void deleteSessionById(Long id);

    void deleteAllSessionsByMovieId(Long movieId);

    List<CinemaHallTO> getAllCinemaHall();

    Long addCinemaHall(CinemaHallTO cinemaHall);

    Long deleteCinemaHallById(Long id);

    CinemaHallTO getCinemaHallById(Long id);
}
