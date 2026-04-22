package io.circuitmind.observability;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public class CircuitMindMetrics {

    private final Counter errorCounter;
    private final Counter retryCounter;
    private final Counter fallbackCounter;

    public CircuitMindMetrics(MeterRegistry registry) {
        this.errorCounter = registry.counter("circuitmind.errors");
        this.retryCounter = registry.counter("circuitmind.retries");
        this.fallbackCounter = registry.counter("circuitmind.fallbacks");
    }

    public void incrementError() {
        errorCounter.increment();
    }

    public void incrementRetry() {
        retryCounter.increment();
    }

    public void incrementFallback() {
        fallbackCounter.increment();
    }
}