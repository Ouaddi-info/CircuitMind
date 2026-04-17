package model;

import java.util.List;

public class ErrorPatternDefinition {

    private String code;
    private String category;
    private List<String> matchers;
    private List<String> probableCauses;
    private RecommendedActions recommendedActions;
    private List<String> solutionHints;

    public ErrorPatternDefinition() {
    }

    public ErrorPatternDefinition(String code,
                                  String category,
                                  List<String> matchers,
                                  List<String> probableCauses,
                                  RecommendedActions recommendedActions,
                                  List<String> solutionHints) {
        this.code = code;
        this.category = category;
        this.matchers = matchers;
        this.probableCauses = probableCauses;
        this.recommendedActions = recommendedActions;
        this.solutionHints = solutionHints;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getMatchers() {
        return matchers;
    }

    public void setMatchers(List<String> matchers) {
        this.matchers = matchers;
    }

    public List<String> getProbableCauses() {
        return probableCauses;
    }

    public void setProbableCauses(List<String> probableCauses) {
        this.probableCauses = probableCauses;
    }

    public RecommendedActions getRecommendedActions() {
        return recommendedActions;
    }

    public void setRecommendedActions(RecommendedActions recommendedActions) {
        this.recommendedActions = recommendedActions;
    }

    public List<String> getSolutionHints() {
        return solutionHints;
    }

    public void setSolutionHints(List<String> solutionHints) {
        this.solutionHints = solutionHints;
    }
}
