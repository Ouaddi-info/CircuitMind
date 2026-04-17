import decision.ErrorDecision;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorClassifierTest {

    @Test
    void shouldClassifyTimeoutError() {
        JsonKnowledgeLoader loader = new JsonKnowledgeLoader();
        ProjectKnowledge knowledge = loader.loadFromClasspath("knowledge-base.json");

        JsonKnowledgeBase knowledgeBase = new JsonKnowledgeBase(knowledge);
        DefaultErrorClassifier classifier = new DefaultErrorClassifier(knowledgeBase);

        RuntimeException error = new RuntimeException("Read timed out");

        ErrorDecision decision = classifier.classify(error, "payment-service");

        assertNotNull(decision);
        assertEquals("TIMEOUT", decision.getCategory());
        assertTrue(decision.isShouldRetry());
        assertEquals("payment-service", decision.getServiceName());
    }

    @Test
    void shouldReturnUnknownWhenNoPatternMatches() {
        JsonKnowledgeLoader loader = new JsonKnowledgeLoader();
        ProjectKnowledge knowledge = loader.loadFromClasspath("knowledge-base.json");

        JsonKnowledgeBase knowledgeBase = new JsonKnowledgeBase(knowledge);
        DefaultErrorClassifier classifier = new DefaultErrorClassifier(knowledgeBase);

        RuntimeException error = new RuntimeException("Some unexpected business error");

        ErrorDecision decision = classifier.classify(error, "payment-service");

        assertNotNull(decision);
        assertEquals("UNKNOWN", decision.getCategory());
        assertFalse(decision.isShouldRetry());
    }
}
