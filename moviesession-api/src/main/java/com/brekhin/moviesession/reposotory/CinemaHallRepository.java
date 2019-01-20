package com.brekhin.moviesession.reposotory;

import com.brekhin.moviesession.entity.CinemaHallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHallEntity, Long> {
}
