package dev.yoghurt1131.springretrytestextension.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpServerErrorException;

public class SampleServiceImpl implements SampleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleServiceImpl.class);

    @Override
    @Retryable(value = HttpServerErrorException.class, maxAttempts = 2)
    public void voidCall(HttpStatus status) {
        LOGGER.info("Argument: " + status.value());
        if(status.is5xxServerError()) {
            throw new HttpServerErrorException(status);
        }
    }

    @Recover
    private void recoverVoidCall(HttpServerErrorException exception, HttpStatus status) {
        LOGGER.info("Recovered");
    }

    @Override
    public String returnableCall(HttpStatus status) {
        LOGGER.info("Argument: " + status.value());
        if(status.is5xxServerError()) {
            throw new HttpServerErrorException(status);
        }
        return "Success: " + status;
    }

    @Recover
    private String recoverReturnableCall(HttpServerErrorException exception, HttpStatus status) {
        LOGGER.info("Recovered");
        return "Recovered: " + status;
    }
}
