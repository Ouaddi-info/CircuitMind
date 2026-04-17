package decision;

import java.util.List;

public class ErrorDecision {

    private String serviceName;
    private String category;
    private String probableCause;
    private boolean shouldRetry;
    private boolean shouldOpenCircuit;
    private String fallback;
    private List<String> hints;

    public ErrorDecision() {
    }

    public ErrorDecision(String serviceName,
                         String category,
                         String probableCause,
                         boolean shouldRetry,
                         boolean shouldOpenCircuit,
                         String fallback,
                         List<String> hints) {
        this.serviceName = serviceName;
        this.category = category;
        this.probableCause = probableCause;
        this.shouldRetry = shouldRetry;
        this.shouldOpenCircuit = shouldOpenCircuit;
        this.fallback = fallback;
        this.hints = hints;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProbableCause() {
        return probableCause;
    }

    public void setProbableCause(String probableCause) {
        this.probableCause = probableCause;
    }

    public boolean isShouldRetry() {
        return shouldRetry;
    }

    public void setShouldRetry(boolean shouldRetry) {
        this.shouldRetry = shouldRetry;
    }

    public boolean isShouldOpenCircuit() {
        return shouldOpenCircuit;
    }

    public void setShouldOpenCircuit(boolean shouldOpenCircuit) {
        this.shouldOpenCircuit = shouldOpenCircuit;
    }

    public String getFallback() {
        return fallback;
    }

    public void setFallback(String fallback) {
        this.fallback = fallback;
    }

    public List<String> getHints() {
        return hints;
    }

    public void setHints(List<String> hints) {
        this.hints = hints;
    }
}
