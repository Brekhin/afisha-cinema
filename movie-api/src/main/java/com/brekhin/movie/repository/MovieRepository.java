package com.brekhin.movie.repository;

import com.brekhin.movie.entity.MovieEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<MovieEntity, UUID> {

    Optional<MovieEntity> findById(UUID uuid);
}
