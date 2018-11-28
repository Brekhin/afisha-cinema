package com.brekhin.gateway.grpc.client;

import com.brekhin.movie.grpc.model.gRPCAddMovieRequest;
import com.brekhin.movie.grpc.model.gRPCAddMovieResponse;
import com.brekhin.movie.grpc.service.MovieServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GRpcMovieServiceClient {

    public static Logger logger = LoggerFactory.getLogger(GRpcMovieServiceClient.class);
    private MovieServiceGrpc.MovieServiceBlockingStub movieServiceBlockingStub;

    @Value("#{new String('${gateway.grpc.client.MovieServiceGrpc.host}')}")
    private String host;

    @Value("#{new Integer('${gateway.grpc.client.MovieServiceGrpc.port}')}")
    private Integer port;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        movieServiceBlockingStub = MovieServiceGrpc.newBlockingStub(managedChannel);
    }

    public gRPCAddMovieResponse addMovie(gRPCAddMovieRequest request) {
        try {
            return movieServiceBlockingStub.addMovie(request);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
