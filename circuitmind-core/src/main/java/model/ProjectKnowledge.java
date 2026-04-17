package model;

import java.util.List;

public class ProjectKnowledge {

    private List<ServiceDefinition> services;
    private List<ErrorPatternDefinition> errorPatterns;
    private List<CircuitPolicyDefinition> circuitPolicies;

    public ProjectKnowledge() {
    }

    public List<ServiceDefinition> getServices() {
        return services;
    }

    public void setServices(List<ServiceDefinition> services) {
        this.services = services;
    }

    public List<ErrorPatternDefinition> getErrorPatterns() {
        return errorPatterns;
    }

    public void setErrorPatterns(List<ErrorPatternDefinition> errorPatterns) {
        this.errorPatterns = errorPatterns;
    }

    public List<CircuitPolicyDefinition> getCircuitPolicies() {
        return circuitPolicies;
    }

    public void setCircuitPolicies(List<CircuitPolicyDefinition> circuitPolicies) {
        this.circuitPolicies = circuitPolicies;
    }
}