package com.brekhin.movie.grpc.service.impl;

import com.brekhin.movie.converter.ProtoConvertToEntity;
import com.brekhin.movie.entity.MovieEntity;
import com.brekhin.movie.grpc.model.gRPCAddMovieRequest;
import com.brekhin.movie.grpc.model.gRPCAddMovieResponse;
import com.brekhin.movie.grpc.model.gRPCGetMovieRequest;
import com.brekhin.movie.grpc.model.gRPCGetMovieResponse;
import com.brekhin.movie.service.MovieService;
import io.grpc.stub.StreamObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceGrpcImplTest {

    private static final Long MOVIE_ID =145L;

    private static final String NAME = "GOT";

    private static final String GENRE = "Fantasy";

    private static final int DURATION = 45;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieServiceGrpcImpl movieServiceGrpc;

    @Test
    public void testGetMovieByID() {
        MovieEntity serviceResponse = getMockMovieEntity();
        when(movieService.getMovie(any(Long.class))).thenReturn(serviceResponse);

        gRPCGetMovieRequest request = gRPCGetMovieRequest.newBuilder()
                .setMovieId(MOVIE_ID)
                .build();
        StreamObserver<gRPCGetMovieResponse> observer = mock(StreamObserver.class);

        movieServiceGrpc.getMovie(request, observer);

        verify(observer, times(1)).onCompleted();

        ArgumentCaptor<gRPCGetMovieResponse> argumentCaptor = ArgumentCaptor.forClass(gRPCGetMovieResponse.class);
        verify(observer, times(1)).onNext(argumentCaptor.capture());

        gRPCGetMovieResponse response = argumentCaptor.getValue();

        assertEquals(java.util.Optional.ofNullable(response.getMovie().getMovieId()).get(), MOVIE_ID);
    }


    @Test
    public void addMovieTest() {
        MovieEntity movieEntity = getMockMovieEntity();

        when(movieService.addMovie(any(MovieEntity.class))).thenReturn(MOVIE_ID);

        gRPCAddMovieRequest request = gRPCAddMovieRequest.newBuilder()
                .setMovie(ProtoConvertToEntity.convert(getMockMovieEntity()))
                .build();

        StreamObserver<gRPCAddMovieResponse> observer = mock(StreamObserver.class);

        movieServiceGrpc.addMovie(request, observer);
        verify(observer, times(1)).onCompleted();

        ArgumentCaptor<gRPCAddMovieResponse> captor = ArgumentCaptor.forClass(gRPCAddMovieResponse.class);
        verify(observer, times(1)).onNext(captor.capture());

        gRPCAddMovieResponse response = captor.getValue();
        assertEquals(java.util.Optional.ofNullable(response.getMovieId()).get(), MOVIE_ID);
    }


    private MovieEntity getMockMovieEntity() {
        return new MovieEntity()
                .setMovieId(MOVIE_ID)
                .setName(NAME)
                .setGenre(GENRE)
                .setDuration(DURATION);
    }


}
