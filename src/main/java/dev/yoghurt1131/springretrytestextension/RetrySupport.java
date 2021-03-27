package dev.yoghurt1131.springretrytestextension;

import org.springframework.aop.framework.ProxyFactory;

public class RetrySupport {

    public static <T> T enableRetry(T object) {
        RetryableMethodInterceptor interceptor = new RetryableMethodInterceptor();
        ProxyFactory proxyFactory = new ProxyFactory(object);
        proxyFactory.addAdvice(interceptor);
        return (T) proxyFactory.getProxy();
    }
}
