package dev.yoghurt1131.springretrytestextension;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

public class SampleService {
    @Retryable(value = RuntimeException.class, maxAttempts = 2)
    public void voidCall(String text) {
        if("ERROR".equals(text)) {
            throw new RuntimeException("Retryable Error");
        }
    }

    @Recover
    private void recoverVoidCall(RuntimeException exception, String text) {
    }

}
