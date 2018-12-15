package com.brekhin.gateway.service;

import com.brekhin.gateway.web.to.in.moviesession.AddDateOfSessionRequest;
import com.brekhin.gateway.web.to.in.moviesession.AddTimeOfSessionRequest;
import com.brekhin.gateway.web.to.out.moviesession.AddDateOfSessionResponse;
import com.brekhin.gateway.web.to.out.moviesession.AddTimeOfSessionResponse;

import java.util.List;

public interface MovieSessionService {

    Long addDateOfSession(AddDateOfSessionRequest request);
    List<AddDateOfSessionRequest> getAllDateOfSession();
    Long addTimeOfSession(AddTimeOfSessionRequest request);
    AddDateOfSessionResponse getAllSessionByDate(Long dateOfSessionId);
    AddTimeOfSessionResponse getInfoAboutTimeOfSessionInSpecificDay(Long timeOfSessionId, Long dateOfSessionId);
    void assigneTimeOfSessionsWithDay(Long timeOfSessionId, Long dateOfSessionId);
    void assignMovieWithSession(Long timeOfSessionId, Long movieId);
}
