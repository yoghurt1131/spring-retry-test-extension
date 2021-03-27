package dev.yoghurt1131.springretrytestextension;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.RecoverAnnotationRecoveryHandler;
import org.springframework.retry.annotation.Retryable;

import java.lang.reflect.Method;

class RetryableMethodInterceptor implements MethodInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryableMethodInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method calledMethod = invocation.getMethod();
        Method actualMethod = invocation.getMethod().getDeclaringClass().isInterface() ?
                invocation.getThis().getClass().getMethod(calledMethod.getName(), calledMethod.getParameterTypes()) :
                invocation.getMethod();
        if(calledMethod.getAnnotation(Retryable.class) == null
            && actualMethod.getAnnotation(Retryable.class) == null) {
            LOGGER.debug("@Retryable Annotation does not exist.");
            return invocation.proceed();
        }
        try {
            LOGGER.debug("Invoke Method.");
            Object returnValue = invocation.proceed();
            return returnValue;
        } catch(Throwable throwable) {
            LOGGER.debug("Catch Throwable.");
            return this.recover(invocation, throwable);
        }
    }

    private <T> T recover(MethodInvocation invocation, Throwable throwable) {
        RecoverAnnotationRecoveryHandler<T> handler =
                new RecoverAnnotationRecoveryHandler(invocation.getThis(), invocation.getMethod());
        return handler.recover(invocation.getArguments(), throwable);
    }
}
