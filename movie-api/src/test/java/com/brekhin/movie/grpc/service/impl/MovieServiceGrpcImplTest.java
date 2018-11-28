package com.brekhin.movie.grpc.service.impl;

import com.brekhin.movie.converter.ProtoConvertToEntity;
import com.brekhin.movie.entity.MovieEntity;
import com.brekhin.movie.grpc.GUuid;
import com.brekhin.movie.grpc.model.*;
import com.brekhin.movie.service.MovieService;
import io.grpc.stub.StreamObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceGrpcImplTest {

    private static final UUID MOVIE_UUID = UUID.fromString("fc4ca5a6-0cda-43df-967b-59883afc3964");

    private static final String NAME = "GOT";

    private static final Timestamp RENTAL_START_DATE = Timestamp.valueOf("2012-08-08 00:00:00");

    private static final Timestamp RENTAL_END_DATE = Timestamp.valueOf("2020-08-08 00:00:00");

    private static final String GENRE = "Fantasy";

    private static final int DURATION = 45;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieServiceGrpcImpl movieServiceGrpc;

    @Test
    public void testGetMovieByID() {
        MovieEntity serviceResponse = getMockMovieEntity();
        when(movieService.getMovie(any(UUID.class))).thenReturn(serviceResponse);

        gRPCGetMovieRequest request = gRPCGetMovieRequest.newBuilder()
                .setMovieId(GUuid.newBuilder().setUuid(MOVIE_UUID.toString()).build())
                .build();
        StreamObserver<gRPCGetMovieResponse> observer = mock(StreamObserver.class);

        movieServiceGrpc.getMovie(request, observer);

        verify(observer, times(1)).onCompleted();

        ArgumentCaptor<gRPCGetMovieResponse> argumentCaptor = ArgumentCaptor.forClass(gRPCGetMovieResponse.class);
        verify(observer, times(1)).onNext(argumentCaptor.capture());

        gRPCGetMovieResponse response = argumentCaptor.getValue();

        assertEquals(response.getMovie().getUuid().getUuid(), MOVIE_UUID.toString());
        assertEquals(new Timestamp(response.getMovie().getRentalStartDate()), RENTAL_START_DATE);
        assertEquals(new Timestamp(response.getMovie().getRentalEndDate()), RENTAL_END_DATE);
    }


    @Test
    public void addMovieTest() {
        MovieEntity movieEntity = getMockMovieEntity();

        when(movieService.addMovie(any(MovieEntity.class))).thenReturn(MOVIE_UUID);

        gRPCAddMovieRequest request = gRPCAddMovieRequest.newBuilder()
                .setMovie(ProtoConvertToEntity.convert(getMockMovieEntity()))
                .build();

        StreamObserver<gRPCAddMovieResponse> observer = mock(StreamObserver.class);

        movieServiceGrpc.addMovie(request, observer);
        verify(observer, times(1)).onCompleted();

        ArgumentCaptor<gRPCAddMovieResponse> captor = ArgumentCaptor.forClass(gRPCAddMovieResponse.class);
        verify(observer, times(1)).onNext(captor.capture());

        gRPCAddMovieResponse response = captor.getValue();
        assertEquals(response.getMovieId(), ProtoConvertToEntity.convert(MOVIE_UUID));

    }


    private MovieEntity getMockMovieEntity() {
        return new MovieEntity()
                .setMovieId(MOVIE_UUID)
                .setName(NAME)
                .setRentalStartDate(RENTAL_START_DATE)
                .setRentalEndDate(RENTAL_END_DATE)
                .setGenre(GENRE)
                .setDuration(DURATION);
    }


}
