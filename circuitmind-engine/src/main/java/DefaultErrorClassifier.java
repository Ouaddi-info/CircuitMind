import decision.ErrorDecision;
import model.ErrorPatternDefinition;
import spi.KnowledgeBase;

import java.util.List;
import java.util.Optional;

public class DefaultErrorClassifier {

    private final KnowledgeBase knowledgeBase;

    public DefaultErrorClassifier(KnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    public ErrorDecision classify(Throwable error, String serviceName) {
        Optional<ErrorPatternDefinition> patternOpt = knowledgeBase.findMatch(error, serviceName);

        if (patternOpt.isEmpty()) {
            return new ErrorDecision(
                    serviceName,
                    "UNKNOWN",
                    error.getClass().getSimpleName(),
                    false,
                    false,
                    "default-fallback",
                    List.of("inspect logs", "add new rule to knowledge base")
            );
        }

        ErrorPatternDefinition pattern = patternOpt.get();

        String probableCause = pattern.getProbableCauses() != null && !pattern.getProbableCauses().isEmpty()
                ? pattern.getProbableCauses().get(0)
                : "unknown";

        return new ErrorDecision(
                serviceName,
                pattern.getCategory(),
                probableCause,
                pattern.getRecommendedActions() != null && pattern.getRecommendedActions().isRetry(),
                pattern.getRecommendedActions() != null && pattern.getRecommendedActions().isOpenCircuit(),
                pattern.getRecommendedActions() != null ? pattern.getRecommendedActions().getFallback() : "default-fallback",
                pattern.getSolutionHints()
        );
    }
}
