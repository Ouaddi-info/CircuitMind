package engine;

import decision.ErrorDecision;
import policy.RuntimePolicy;
import spi.KnowledgeBase;
import spi.PolicyResolver;

public class CircuitMindEngine {

    private final DefaultErrorClassifier classifier;
    private final PolicyResolver policyResolver;

    public CircuitMindEngine(KnowledgeBase knowledgeBase) {
        this.classifier = new DefaultErrorClassifier(knowledgeBase);
        this.policyResolver = new DefaultPolicyResolver();
    }

    public RuntimePolicy analyze(Throwable error, String serviceName) {
        ErrorDecision decision = classifier.classify(error, serviceName);
        return policyResolver.resolve(decision);
    }
}
