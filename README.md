# Spring Retry Test Extension

A test support library for [spring-retry](https://github.com/spring-projects/spring-retry).

# How to use

0. clone this project and install jar (it will be removed soon.)
    ```bash
    $ git clone git@github.com:yoghurt1131/spring-retry-test-extension.git
    $ cd spring-retry-test-extension
    $ mvn install
    ```
1. add spring-retry-text-extension to test scope dependency in pom.xml
    ```xml
    <dependency>
        <groupId>dev.yoghurt1131</groupId>
        <artifactId>spring-retry-test-extension</artifactId>
        <version>0.1.0-SNAPSHOT</version>
        <scope>test</scope>
    </dependency>
    ```
2. Use `RetrySupport.enableRetry` in your retryable test class. 
    SampleService.java
    ```java
    public class SampleService {
        @Retryable(value = RuntimeException.class, maxAttempts = 2)
        public void voidCall(String text) {
            if("ERROR".equals(text)) { throw new RuntimeException("Retryable Error"); }
        }

        @Recover
        private void recoverVoidCall(RuntimeException exception, String text) {
        }
    }
    ```
    SampleServiceTest.java
    ```java
    public class RetrySupportTest {
        @Test // assert no exception thrown
        public void testVoidCallRetry() {
            SampleService service = RetrySupport.enableRetry(new SampleService());
            service.voidCall("ERROR");
        }
    }
    ```
 
 # Support OSS

- support Spring Framework 5.3.1
- support Spring Retry 1.3.1

# Limitation

- [Mockito's](https://github.com/mockito/mockito) Spy doesn't work with `RetrySupport` as expected
