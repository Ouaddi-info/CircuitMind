package starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "circuitmind")
public class CircuitMindProperties {

    private boolean enabled = true;
    private String knowledgeBasePath = "knowledge-base.json";
    private String defaultFallback = "default-fallback";

    private Retry retry = new Retry();
    private Circuit circuit = new Circuit();
    private Observability observability = new Observability();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getKnowledgeBasePath() {
        return knowledgeBasePath;
    }

    public void setKnowledgeBasePath(String knowledgeBasePath) {
        this.knowledgeBasePath = knowledgeBasePath;
    }

    public String getDefaultFallback() {
        return defaultFallback;
    }

    public void setDefaultFallback(String defaultFallback) {
        this.defaultFallback = defaultFallback;
    }

    public Retry getRetry() {
        return retry;
    }

    public void setRetry(Retry retry) {
        this.retry = retry;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public Observability getObservability() {
        return observability;
    }

    public void setObservability(Observability observability) {
        this.observability = observability;
    }

    public static class Retry {
        private int maxAttempts = 3;
        private long backoffMs = 500;

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
    }

    public static class Circuit {
        private float failureRateThreshold = 50f;
        private int slidingWindowSize = 10;
        private int minimumNumberOfCalls = 5;
        private long waitDurationOpenStateMs = 10000;

        public float getFailureRateThreshold() {
            return failureRateThreshold;
        }

        public void setFailureRateThreshold(float failureRateThreshold) {
            this.failureRateThreshold = failureRateThreshold;
        }

        public int getSlidingWindowSize() {
            return slidingWindowSize;
        }

        public void setSlidingWindowSize(int slidingWindowSize) {
            this.slidingWindowSize = slidingWindowSize;
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

    public static class Observability {
        private boolean enabled = true;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
