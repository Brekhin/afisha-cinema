package com.brekhin.moviesession.service;

import com.brekhin.moviesession.entity.DateOfSessionEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;

import java.util.Set;


public interface CinemaSessionService {

    Long addTimeOfSession(TimeOfSessionEntity movieSessionEntity);

    Long addDateOfSession(DateOfSessionEntity movieSessionEntity);

    // получить все сеансы в определенный день
    Set<TimeOfSessionEntity> getAllSessionsByDate(Long DateOfSessionId);

    // получить информацию об определенном сеансе в определенный день
    TimeOfSessionEntity getInfoAboutSesionByDate(Long movieByTimeOfSessionId, Long movieByDateId);

    void assigneTimeOfSessionsWithDay(Long timeOfSessionId, Long dateOfSessionId);

    void assignMovieWithSession(Long timeOfSessionId, Long movieId);
}
