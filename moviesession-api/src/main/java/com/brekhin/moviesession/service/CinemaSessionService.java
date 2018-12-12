package com.brekhin.moviesession.service;

import com.brekhin.moviesession.entity.DateOfSessionEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;

import java.util.List;
import java.util.Set;


public interface CinemaSessionService {

    Long addTimeOfSession(TimeOfSessionEntity movieSessionEntity);

    Long addDateOfSession(DateOfSessionEntity movieSessionEntity);

    // получить информацию о всех сеансах в определенный день
    DateOfSessionEntity getAllSessionsByDate(Long movieByDateId);

    // получить информацию об определенном сеансе в определенный день
    TimeOfSessionEntity getInfoAboutTimeOfSessionInSpecificDay(Long timeOfSessionId, Long dateOfSessionId);

    List<DateOfSessionEntity> getAllDateOfSessions();

    void assigneTimeOfSessionsWithDay(Long movieByTimeOfSessionId, Long movieByDateId);

    void assignMovieWithSession(Long timeOfSessionId, Long movieId);
}
