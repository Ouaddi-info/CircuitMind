package starter.resilience;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import policy.RuntimePolicy;
import starter.config.CircuitMindProperties;

import java.time.Duration;
import java.util.concurrent.Callable;

public class ResilienceExecutor {
    private final CircuitMindProperties properties;

    public ResilienceExecutor(CircuitMindProperties properties) {
        this.properties = properties;
    }

    public <T> T execute(String serviceName, RuntimePolicy policy, Callable<T> action) throws Exception {
        Callable<T> decorated = action;

        if (policy.isRetry()) {
            RetryConfig retryConfig = RetryConfig.custom()
                    .maxAttempts(Math.max(policy.getMaxAttempts(), properties.getRetry().getMaxAttempts()))
                    .waitDuration(Duration.ofMillis(
                            policy.getBackoffMs() > 0 ? policy.getBackoffMs() : properties.getRetry().getBackoffMs()
                    ))
                    .build();

            Retry retry = Retry.of(serviceName + "-retry", retryConfig);
            decorated = Retry.decorateCallable(retry, decorated);
        }

        if (policy.isOpenCircuit()) {
            CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                    .failureRateThreshold(properties.getCircuit().getFailureRateThreshold())
                    .waitDurationInOpenState(Duration.ofMillis(properties.getCircuit().getWaitDurationOpenStateMs()))
                    .slidingWindowSize(properties.getCircuit().getSlidingWindowSize())
                    .minimumNumberOfCalls(properties.getCircuit().getMinimumNumberOfCalls())
                    .build();

            CircuitBreaker circuitBreaker = CircuitBreaker.of(serviceName + "-circuit", circuitBreakerConfig);
            decorated = CircuitBreaker.decorateCallable(circuitBreaker, decorated);
        }

        return decorated.call();
    }
}
