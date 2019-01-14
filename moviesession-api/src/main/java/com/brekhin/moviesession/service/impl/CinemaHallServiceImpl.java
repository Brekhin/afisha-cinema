package com.brekhin.moviesession.service.impl;

import com.brekhin.moviesession.entity.CinemaHallEntity;
import com.brekhin.moviesession.reposotory.CinemaHallRepository;
import com.brekhin.moviesession.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {

    private final CinemaHallRepository cinemaHallRepository;

    @Autowired
    public CinemaHallServiceImpl(CinemaHallRepository cinemaHallRepository) {
        this.cinemaHallRepository = cinemaHallRepository;
    }

    @Override
    public List<CinemaHallEntity> getAllCinemaHall() {
        return cinemaHallRepository.findAll();
    }

    @Override
    public Long addCinemaHall(CinemaHallEntity cinemaHall) {
        return cinemaHallRepository.save(cinemaHall).getHallId();
    }

    @Override
    public Long deleteCinemaHallById(Long id) {
        cinemaHallRepository.findById(id)
                .ifPresent(cinemaHallRepository::delete);
        return id;
    }

    @Override
    public CinemaHallEntity getCinemaHallById(Long id) {
        return cinemaHallRepository.findById(id).get();
    }
}
