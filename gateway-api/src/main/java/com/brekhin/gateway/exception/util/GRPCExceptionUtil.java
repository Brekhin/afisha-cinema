package com.brekhin.gateway.exception.util;

import com.brekhin.gateway.exception.GatewayAPIException;
import com.brekhin.gateway.exception.service.Service;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.http.HttpStatus;

public class GRPCExceptionUtil {

    public static GatewayAPIException movieModuleException(StatusRuntimeException exception){
        return convert(Service.MOVIES, exception);
    }

    private static GatewayAPIException convert(Service service, StatusRuntimeException exception) {
        return new GatewayAPIException(service, convertToHttpStatus(exception.getStatus()), exception.getMessage());
    }

    private static HttpStatus convertToHttpStatus(Status status) {
        switch (status.getCode()) {
            case OK:
                return HttpStatus.OK;
            case UNAUTHENTICATED:
                return HttpStatus.UNAUTHORIZED;
            case PERMISSION_DENIED:
                return HttpStatus.FORBIDDEN;
            case NOT_FOUND:
                return HttpStatus.NOT_FOUND;
            case UNAVAILABLE:
                return HttpStatus.SERVICE_UNAVAILABLE;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
