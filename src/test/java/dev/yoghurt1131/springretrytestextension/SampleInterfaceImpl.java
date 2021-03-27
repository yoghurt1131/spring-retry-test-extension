package dev.yoghurt1131.springretrytestextension;

import org.springframework.retry.annotation.Recover;

public class SampleInterfaceImpl implements SampleInterface {

    @Override
    public String returnableCall(String text) {
        if("ERROR".equals(text)) {
            throw new RuntimeException("Retryable Error");
        }
        return "Success: " + text;
    }

    @Recover
    private String recoverReturnableCall(RuntimeException exception, String text) {
        return "Recovered: " + text;
    }
}
