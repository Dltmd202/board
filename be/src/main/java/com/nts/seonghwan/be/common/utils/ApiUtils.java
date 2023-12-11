package com.nts.seonghwan.be.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class ApiUtils {
    private static final ThreadLocal<ObjectMapper> threadSafeObjectMapper =
            ThreadLocal.withInitial(ObjectMapper::new);

    public static <T> ApiResult<T> success(T response){
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> error(Throwable throwable, HttpStatus status){
        return new ApiResult<>(false, null, new ApiError(throwable, status));
    }

    public static <T> ApiResult<T> error(T message, HttpStatus status){
        return new ApiResult<>(false, null, new ApiError(message, status));
    }

    public static ApiResult<?> error(HttpStatus status){
        return new ApiResult<>(false, null, new ApiError(status.getReasonPhrase(), status));
    }

    @Getter
    public static class ApiError<T>{
        private final T message;
        private final int status;

        ApiError(Throwable throwable, HttpStatus status){
            this.message = (T) throwable.getMessage();
            this.status = status.value();
        }

        ApiError(T message, HttpStatus status){
            this.message = message;
            this.status = status.value();
        }
    }

    @RequiredArgsConstructor
    @Getter
    public static class ApiResult<T>{
        private final boolean success;
        private final T response;
        private final ApiError error;

        public String toString() {
            ObjectMapper objectMapper = threadSafeObjectMapper.get();
            try {
                return objectMapper.writeValueAsString(this);
            } catch (JsonProcessingException e) {
                return "{}";
            }
        }

    }
}
