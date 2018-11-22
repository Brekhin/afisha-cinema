package com.brekhin.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<com.brekhin.movie.entity.MovieEntity, UUID> {
}
