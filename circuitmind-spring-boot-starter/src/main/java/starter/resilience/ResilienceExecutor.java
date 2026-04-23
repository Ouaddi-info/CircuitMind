package starter.resilience;


import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import policy.RuntimePolicy;

import java.time.Duration;
import java.util.concurrent.Callable;

public class ResilienceExecutor {

    public <T> T execute(String serviceName, RuntimePolicy policy, Callable<T> action) throws Exception {
        Callable<T> decorated = action;

        if (policy.isRetry()) {
            RetryConfig retryConfig = RetryConfig.custom()
                    .maxAttempts(Math.max(policy.getMaxAttempts(), 1))
                    .waitDuration(Duration.ofMillis(Math.max(policy.getBackoffMs(), 1)))
                    .build();

            Retry retry = Retry.of(serviceName + "-retry", retryConfig);
            decorated = Retry.decorateCallable(retry, decorated);
        }

        if (policy.isOpenCircuit()) {
            CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                    .failureRateThreshold(50)
                    .waitDurationInOpenState(Duration.ofSeconds(10))
                    .slidingWindowSize(10)
                    .minimumNumberOfCalls(5)
                    .build();

            CircuitBreaker circuitBreaker = CircuitBreaker.of(serviceName + "-circuit", circuitBreakerConfig);
            decorated = CircuitBreaker.decorateCallable(circuitBreaker, decorated);
        }

        return decorated.call();
    }
}
