package com.brekhin.moviesession.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cinema_hall", schema = "time_of_session")
public class CinemaHallEntity {

    private Long hallId;
    private String name;
    private int seatCount;

    @Id
    @Column(name = "hallId")
    public Long getHallId() {
        return hallId;
    }

    public CinemaHallEntity setHallId(Long hallId) {
        this.hallId = hallId;
        return this;
    }

    @Column(name = "seatCount")
    public int getSeatCount() {
        return seatCount;
    }

    public CinemaHallEntity setSeatCount(int seatCount) {
        this.seatCount = seatCount;
        return this;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public CinemaHallEntity setName(String name) {
        this.name = name;
        return this;
    }
}
