package com.brekhin.session.reposotory;

import com.brekhin.session.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository  extends JpaRepository<SessionEntity, Long> {
}
