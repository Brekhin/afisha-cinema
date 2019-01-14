package com.brekhin.moviesession.service;

import com.brekhin.moviesession.entity.CinemaHallEntity;

import java.util.List;

public interface CinemaHallService {

    List<CinemaHallEntity> getAllCinemaHall();

    Long addCinemaHall(CinemaHallEntity cinemaHall);

    Long deleteCinemaHallById(Long id);

    CinemaHallEntity getCinemaHallById(Long id);
}
