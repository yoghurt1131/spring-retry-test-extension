package dev.yoghurt1131.springretrytestextension.sample;

import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpServerErrorException;

public interface SampleService {

    void voidCall(HttpStatus status);

    @Retryable(value = HttpServerErrorException.class, maxAttempts = 2)
    String returnableCall(HttpStatus status);
}
