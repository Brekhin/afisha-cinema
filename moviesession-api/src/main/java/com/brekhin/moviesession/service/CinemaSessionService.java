package com.brekhin.moviesession.service;

import com.brekhin.moviesession.entity.TimeOfSessionEntity;

import java.util.Collection;


public interface CinemaSessionService {

    Long addTimeOfSession(TimeOfSessionEntity movieSessionEntity);

    TimeOfSessionEntity getTimeOfSessionById(Long timeOfSessionId);

    Collection<TimeOfSessionEntity> getSessionsByMovieId(Long movieId);

    void deleteSessionById(Long sessionId);

    void deleteSessionsByMovieId(Long movieId);

    void assignHallAndSession(Long hallId, Long sessionId);
}
