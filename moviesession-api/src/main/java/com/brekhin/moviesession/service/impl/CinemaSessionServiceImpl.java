package com.brekhin.moviesession.service.impl;

import com.brekhin.moviesession.entity.DateOfSessionEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;
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
    public Set<TimeOfSessionEntity> getAllSessionsByDate(Long dateOfSessionId) {
        return dateOfSessionRepository.findById(dateOfSessionId).get().getTimeOfSessionEntitie();
    }

    @Override
    public TimeOfSessionEntity getInfoAboutSesionByDate(Long timeOfSessionId, Long dateOfSessionId) {
        return dateOfSessionRepository.getSingleSessionByDateAndTime(timeOfSessionId, dateOfSessionId);
    }

    @Override
    public void assigneTimeOfSessionsWithDay(Long timeOfSessionId, Long dateOfSessionId) {
        Optional<TimeOfSessionEntity> session = timeOfSessionRepository.findById(timeOfSessionId);
        DateOfSessionEntity dateOfSession = dateOfSessionRepository.findById(dateOfSessionId).get().setTimeOfSession(session.get());
        dateOfSessionRepository.save(dateOfSession);
    }

    @Override
    public void assignMovieWithSession(Long timeOfSessionId, Long movieId) {

    }
}
