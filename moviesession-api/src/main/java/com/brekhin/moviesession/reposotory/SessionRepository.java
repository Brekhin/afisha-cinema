package com.brekhin.moviesession.reposotory;

import com.brekhin.moviesession.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository  extends JpaRepository<SessionEntity, Long> {
}
