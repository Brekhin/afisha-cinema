package com.brekhin.moviesession.reposotory;

import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeOfSessionRepository  extends JpaRepository<TimeOfSessionEntity, Long> {
}
