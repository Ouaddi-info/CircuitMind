package model;

public class CircuitPolicyDefinition {

    private String service;
    private int failureRateThreshold;
    private int slowCallRateThreshold;
    private long slowCallDurationMs;
    private int minimumNumberOfCalls;
    private long waitDurationOpenStateMs;

    public CircuitPolicyDefinition() {
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getFailureRateThreshold() {
        return failureRateThreshold;
    }

    public void setFailureRateThreshold(int failureRateThreshold) {
        this.failureRateThreshold = failureRateThreshold;
    }

    public int getSlowCallRateThreshold() {
        return slowCallRateThreshold;
    }

    public void setSlowCallRateThreshold(int slowCallRateThreshold) {
        this.slowCallRateThreshold = slowCallRateThreshold;
    }

    public long getSlowCallDurationMs() {
        return slowCallDurationMs;
    }

    public void setSlowCallDurationMs(long slowCallDurationMs) {
        this.slowCallDurationMs = slowCallDurationMs;
    }

    public int getMinimumNumberOfCalls() {
        return minimumNumberOfCalls;
    }

    public void setMinimumNumberOfCalls(int minimumNumberOfCalls) {
        this.minimumNumberOfCalls = minimumNumberOfCalls;
    }

    public long getWaitDurationOpenStateMs() {
        return waitDurationOpenStateMs;
    }

    public void setWaitDurationOpenStateMs(long waitDurationOpenStateMs) {
        this.waitDurationOpenStateMs = waitDurationOpenStateMs;
    }
}
