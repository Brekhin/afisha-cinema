package com.brekhin.session.reposotory;

import com.brekhin.session.entity.CinemaHallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHallEntity, Long> {
}
