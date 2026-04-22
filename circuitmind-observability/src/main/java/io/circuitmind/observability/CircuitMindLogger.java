package io.circuitmind.observability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import policy.RuntimePolicy;

public class CircuitMindLogger {

    private static final Logger log = LoggerFactory.getLogger("CircuitMind");

    public void logDecision(String service, RuntimePolicy policy) {
        log.info("CircuitMind Decision -> service: {}, retry: {}, attempts: {}, fallback: {}",
                service,
                policy.isRetry(),
                policy.getMaxAttempts(),
                policy.getFallback());
    }
}
