package com.brekhin.gateway.service;

import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.out.moviesession.InfoTimeOfSessionResponse;

import java.util.List;

public interface MovieSessionService {

    Long addTimeOfSession(AddTimeOfSessionRequest request);

    InfoTimeOfSessionResponse getInfoTimeOfSessionById(Long timeOfSessionId);

    List<InfoTimeOfSessionResponse> getSessionsByMovieId(Long movieId);

    void deleteSessionById(Long id);

    void deleteAllSessionsByMovieId(Long movieId);
}
