package com.brekhin.gateway.web.to.out.movie;

import com.brekhin.gateway.web.to.out.moviesession.InfoTimeOfSessionResponse;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GetMovie {

    private final Long movieId;
    private final String name;
    private final String genre;
    private final int duration;
    private List<InfoTimeOfSessionResponse> sessions = new ArrayList<>();

    public GetMovie(Long movieId, String name, String genre, int duration) {
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
    }

    @JsonGetter("movieId")
    public Long getMovieId() {
        return movieId;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("genre")
    public String getGenre() {
        return genre;
    }

    @JsonGetter("duration")
    public int getDuration() {
        return duration;
    }

    @JsonGetter("sessions")
    public List<InfoTimeOfSessionResponse> getSessions() {
        return sessions;
    }
}
