package com.brekhin.moviesession.service.impl;

import com.brekhin.moviesession.entity.DateOfSessionEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import com.brekhin.moviesession.exception.NotFoundSessionException;
import com.brekhin.moviesession.reposotory.DateOfSessionRepository;
import com.brekhin.moviesession.reposotory.TimeOfSessionRepository;
import com.brekhin.moviesession.service.CinemaSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Long addTimeOfSession(TimeOfSessionEntity timeOfSessionEntity) {
        return timeOfSessionRepository.save(timeOfSessionEntity).getTimeOfSessionId();
    }

    @Override
    public Long addDateOfSession(DateOfSessionEntity dateOfSessionEntity) {
        return dateOfSessionRepository.save(dateOfSessionEntity).getDateOfSessionId();
    }

    @Override
    public DateOfSessionEntity getAllSessionsByDate(Long dateOfSessionId) {
        return dateOfSessionRepository.findById(dateOfSessionId)
                .orElseThrow(() ->
                        new NotFoundSessionException("Not found session with id = " + dateOfSessionId.toString())
                );
    }

    @Override
    public TimeOfSessionEntity getInfoAboutTimeOfSessionInSpecificDay(Long timeOfSessionId, Long dateOfSessionId) {
        return dateOfSessionRepository.getSingleSessionByDateAndTime(timeOfSessionId, dateOfSessionId)
                .orElseThrow(() ->
                        new NotFoundSessionException(
                                String.format("Not found DateOfSession with id = %s and TimeOfSession with id = %s",
                                        dateOfSessionId.toString(), timeOfSessionId.toString())

                        ));
    }

    @Override
    public List<DateOfSessionEntity> getAllDateOfSessions() {
        return dateOfSessionRepository.findAll();
    }

    @Override
    public void assigneTimeOfSessionsWithDay(Long timeOfSessionId, Long dateOfSessionId) {
        TimeOfSessionEntity timeOfSessionEntity = timeOfSessionRepository
                .findById(timeOfSessionId)
                .orElseThrow(() ->
                        new NotFoundSessionException(
                                String.format("Not found TimeOfSession with id = %s",
                                        timeOfSessionId.toString())));

        DateOfSessionEntity dateOfSession = dateOfSessionRepository
                .findById(dateOfSessionId)
                .orElseThrow(() ->
                        new NotFoundSessionException(
                                String.format("Not found DateOfSession with id = %s",
                                        dateOfSessionId.toString())
                        )
                );


        dateOfSession.getTimeOfSession().add(timeOfSessionEntity);
        timeOfSessionEntity.setDateOfSession(dateOfSession);

        dateOfSessionRepository.save(dateOfSession);
        timeOfSessionRepository.save(timeOfSessionEntity);
    }

    @Override
    public void assignMovieWithSession(Long timeOfSessionId, Long movieId) {
        TimeOfSessionEntity timeOfSessionEntity = timeOfSessionRepository
                .findById(timeOfSessionId)
                .orElseThrow(() ->
                    new NotFoundSessionException(
                            String.format("Not found TimeOfSession with id = %s",
                                    timeOfSessionId.toString()))
                );

        timeOfSessionEntity.setMovieId(movieId);
        timeOfSessionRepository.save(timeOfSessionEntity);
    }
}
