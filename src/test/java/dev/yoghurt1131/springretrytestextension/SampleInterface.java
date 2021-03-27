package dev.yoghurt1131.springretrytestextension;

import org.springframework.retry.annotation.Retryable;

public interface SampleInterface {

    @Retryable(value = RuntimeException.class, maxAttempts = 2)
    String returnableCall(String text);

}
