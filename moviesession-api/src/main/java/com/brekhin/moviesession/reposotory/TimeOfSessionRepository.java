package com.brekhin.moviesession.reposotory;

import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public interface TimeOfSessionRepository  extends JpaRepository<TimeOfSessionEntity, Long> {
    @Query(
            value = "select * from time_of_session.time_of_session tos where tos.movie_id = ?1",
            nativeQuery = true)
    Collection<TimeOfSessionEntity> getTimeOfSessionEntityListByMovieId(Long movieId);

    @Transactional
    @Modifying
    void deleteAllByMovieId(Long movieId);
}
