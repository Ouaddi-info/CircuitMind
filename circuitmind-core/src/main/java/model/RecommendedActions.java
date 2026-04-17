package model;

public class RecommendedActions {

    private boolean retry;
    private int maxAttempts;
    private long backoffMs;
    private boolean openCircuit;
    private String fallback;

    public RecommendedActions() {
    }

    public RecommendedActions(boolean retry,
                              int maxAttempts,
                              long backoffMs,
                              boolean openCircuit,
                              String fallback) {
        this.retry = retry;
        this.maxAttempts = maxAttempts;
        this.backoffMs = backoffMs;
        this.openCircuit = openCircuit;
        this.fallback = fallback;
    }

    public boolean isRetry() {
        return retry;
    }

    public void setRetry(boolean retry) {
        this.retry = retry;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public long getBackoffMs() {
        return backoffMs;
    }

    public void setBackoffMs(long backoffMs) {
        this.backoffMs = backoffMs;
    }

    public boolean isOpenCircuit() {
        return openCircuit;
    }

    public void setOpenCircuit(boolean openCircuit) {
        this.openCircuit = openCircuit;
    }

    public String getFallback() {
        return fallback;
    }

    public void setFallback(String fallback) {
        this.fallback = fallback;
    }
}
