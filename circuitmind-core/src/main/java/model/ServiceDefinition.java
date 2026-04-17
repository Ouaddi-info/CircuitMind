package model;

import java.util.List;

public class ServiceDefinition {

    private String name;
    private List<String> dependencies;
    private CriticalityLevel criticality;

    public ServiceDefinition() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<String> dependencies) {
        this.dependencies = dependencies;
    }

    public CriticalityLevel getCriticality() {
        return criticality;
    }

    public void setCriticality(CriticalityLevel criticality) {
        this.criticality = criticality;
    }
}
