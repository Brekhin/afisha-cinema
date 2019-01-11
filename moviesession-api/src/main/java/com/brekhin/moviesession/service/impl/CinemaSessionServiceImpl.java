package com.brekhin.moviesession.service.impl;

import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import com.brekhin.moviesession.exception.NotFoundSessionException;
import com.brekhin.moviesession.reposotory.TimeOfSessionRepository;
import com.brekhin.moviesession.service.CinemaSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CinemaSessionServiceImpl implements CinemaSessionService {

    private TimeOfSessionRepository timeOfSessionRepository;

    @Autowired
    public CinemaSessionServiceImpl(TimeOfSessionRepository timeOfSessionRepository) {
        this.timeOfSessionRepository = timeOfSessionRepository;
    }

    @Override
    public Long addTimeOfSession(TimeOfSessionEntity timeOfSessionEntity) {
        return timeOfSessionRepository.save(timeOfSessionEntity).getTimeOfSessionId();
    }

    public TimeOfSessionEntity getTimeOfSessionById(Long timeOfSessionId){
        return timeOfSessionRepository
                .findById(timeOfSessionId)
                .orElseThrow(() ->
                        new NotFoundSessionException(
                                String.format("Not found TimeOfSession with id = %s",
                                        timeOfSessionId.toString()))
                );
    }

    @Override
    public Collection<TimeOfSessionEntity> getSessionsByMovieId(Long movieId) {
        return timeOfSessionRepository.getTimeOfSessionEntityListByMovieId(movieId);
    }

    @Override
    public void deleteSessionById(Long sessionId) {
        Optional<TimeOfSessionEntity> sessionEntity = timeOfSessionRepository.findById(sessionId);
        sessionEntity.ifPresent(timeOfSessionRepository::delete);
    }

    @Override
    public void deleteSessionsByMovieId(Long movieId) {
        timeOfSessionRepository.deleteAllByMovieId(movieId);
    }
}
