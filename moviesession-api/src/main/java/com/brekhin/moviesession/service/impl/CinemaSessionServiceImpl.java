package com.brekhin.moviesession.service.impl;

import com.brekhin.moviesession.entity.DateOfSessionEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import com.brekhin.moviesession.exception.NotFoundSessionException;
import com.brekhin.moviesession.reposotory.DateOfSessionRepository;
import com.brekhin.moviesession.reposotory.TimeOfSessionRepository;
import com.brekhin.moviesession.service.CinemaSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class CinemaSessionServiceImpl implements CinemaSessionService {

    private DateOfSessionRepository dateOfSessionRepository;
    private TimeOfSessionRepository timeOfSessionRepository;

    @Autowired
    public CinemaSessionServiceImpl(DateOfSessionRepository dateOfSessionRepository,
                                    TimeOfSessionRepository timeOfSessionRepository) {
        this.dateOfSessionRepository = dateOfSessionRepository;
        this.timeOfSessionRepository = timeOfSessionRepository;
    }

    @Override
    public Long addTimeOfSession(TimeOfSessionEntity timeOfSession) {
        return timeOfSessionRepository.save(timeOfSession).getTimeOfSessionId();
    }

    @Override
    public Long addDateOfSession(DateOfSessionEntity movieSessionEntity) {
        return dateOfSessionRepository.save(movieSessionEntity).getDateOfSessionId();
    }

    @Override
    public DateOfSessionEntity getAllSessionsByDate(Long dateOfSessionId) {
        return dateOfSessionRepository.findById(dateOfSessionId)
                .orElseThrow(() -> {
                    throw new NotFoundSessionException("Not found session with id = " + dateOfSessionId.toString());
                });
    }

    @Override
    public TimeOfSessionEntity getInfoAboutTimeOfSessionInSpecificDay(Long timeOfSessionId, Long dateOfSessionId) {
        return dateOfSessionRepository.getSingleSessionByDateAndTime(timeOfSessionId, dateOfSessionId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(
                            String.format("Not found DateOfSession with id = %s and TimeOfSession with id = %s",
                                    dateOfSessionId.toString(), timeOfSessionId.toString())
                    );
                });
    }

    @Override
    public List<DateOfSessionEntity> getAllDateOfSessions() {
        return dateOfSessionRepository.findAll();
    }

    @Override
    public void assigneTimeOfSessionsWithDay(Long timeOfSessionId, Long dateOfSessionId) {
        TimeOfSessionEntity timeOfSessionEntity = timeOfSessionRepository
                .findById(timeOfSessionId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(
                            String.format("Not found TimeOfSession with id = %s",
                                    timeOfSessionId.toString()));
                });
        DateOfSessionEntity dateOfSession = dateOfSessionRepository
                .findById(dateOfSessionId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(
                            String.format("Not found DateOfSession with id = %s",
                                    dateOfSessionId.toString()));
                });

        dateOfSession.setTimeOfSession(timeOfSessionEntity);

        dateOfSessionRepository.save(dateOfSession);
    }

    @Override
    public void assignMovieWithSession(Long timeOfSessionId, Long movieId) {
        TimeOfSessionEntity timeOfSessionEntity = timeOfSessionRepository
                .findById(timeOfSessionId)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException(
                            String.format("Not found TimeOfSession with id = %s",
                                    timeOfSessionId.toString()));
                });
        timeOfSessionEntity.setMovieId(movieId);
        timeOfSessionRepository.save(timeOfSessionEntity);
    }
}
