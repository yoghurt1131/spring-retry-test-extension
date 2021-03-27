package dev.yoghurt1131.springretrytestextension.sample;

import dev.yoghurt1131.springretrytestextension.RetrySupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

public class SampleInterfaceTest {

    @Test
    public void testVoidCall_200() {
        SampleService service = new SampleServiceImpl();
        service.voidCall(HttpStatus.OK);
    }

    // assert exception thrown
    @Test
    public void testVoidCall_500() {
        SampleService service = new SampleServiceImpl();
        Assertions.assertThrows(HttpServerErrorException.class, () -> {
            service.voidCall(HttpStatus.INTERNAL_SERVER_ERROR);
        });
    }

    // assert no exception thrown
    @Test
    public void testVoidCallRetry() {
        SampleService service = RetrySupport.enableRetry(new SampleServiceImpl());
        service.voidCall(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testReturnableCall_200() {
        SampleService service = new SampleServiceImpl();
        service.returnableCall(HttpStatus.OK);
    }

    // assert exception thrown
    @Test
    public void testReturnableCall_500() {
        SampleService service = new SampleServiceImpl();
        Assertions.assertThrows(HttpServerErrorException.class, () -> {
            service.returnableCall(HttpStatus.INTERNAL_SERVER_ERROR);
        });
    }

    // assert no exception thrown
    @Test
    public void testReturnableCallRetry() {
        SampleService service = RetrySupport.enableRetry(new SampleServiceImpl());
        service.returnableCall(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
