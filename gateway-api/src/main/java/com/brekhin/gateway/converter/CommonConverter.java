package com.brekhin.gateway.converter;

import com.brekhin.movie.grpc.GUuid;

import java.util.UUID;

public class CommonConverter {
    public static UUID convert(GUuid guuid) {
        return UUID.fromString(guuid.getUuid());
    }

    public static GUuid convert(UUID uuid) {
        return GUuid.newBuilder()
                .setUuid(String.valueOf(uuid))
                .build();
    }
}
