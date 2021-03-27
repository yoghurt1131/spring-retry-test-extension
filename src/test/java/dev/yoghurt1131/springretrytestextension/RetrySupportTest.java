package dev.yoghurt1131.springretrytestextension;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RetrySupportTest {

    @Test
    public void testVoidCall_OK() {
        SampleService service = new SampleService();
        service.voidCall("OK");
    }

    // assert exception thrown
    @Test
    public void testVoidCall_ERROR() {
        SampleService service = new SampleService();
        Assertions.assertThrows(RuntimeException.class, () -> {
            service.voidCall("ERROR");
        });
    }

    // assert no exception thrown
    @Test
    public void testVoidCallRetry() {
        SampleService service = RetrySupport.enableRetry(new SampleService());
        service.voidCall("ERROR");
    }

    @Test
    public void testReturnableCall_OK() {
        SampleInterfaceImpl service = new SampleInterfaceImpl();
        Assertions.assertEquals("Success: OK", service.returnableCall("OK"));
    }

    // assert exception thrown
    @Test
    public void testReturnableCall_ERROR() {
        SampleInterfaceImpl service = new SampleInterfaceImpl();
        Assertions.assertThrows(RuntimeException.class, () -> {
            service.returnableCall("ERROR");
        });
    }

    // assert no exception thrown
    @Test
    public void testReturnableCallRetry() {
        SampleInterface service = RetrySupport.enableRetry(new SampleInterfaceImpl());
        service.returnableCall("ERROR");
    }
}
