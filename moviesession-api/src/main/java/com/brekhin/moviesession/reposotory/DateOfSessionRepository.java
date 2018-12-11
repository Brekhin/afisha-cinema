package com.brekhin.moviesession.reposotory;

import com.brekhin.moviesession.entity.DateOfSessionEntity;
import com.brekhin.moviesession.entity.TimeOfSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DateOfSessionRepository extends JpaRepository<DateOfSessionEntity, Long> {

    @Query(value = "SELECT * FROM moviesession_api.date_of_session ds inner join moviesession_api.time_of_session ts" +
            " WHERE ds.date_of_sessionid = ts.date_of_sessionid and " +
            "ds.date_of_sessionid = ?1 and ts.time_of_session_id = ?2", nativeQuery = true)
    TimeOfSessionEntity getSingleSessionByDateAndTime(Long timeOfSessionId, Long dateOfSessionId);

}
