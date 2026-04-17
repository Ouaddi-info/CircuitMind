import io.circuitmind.json.JsonKnowledgeBase;
import io.circuitmind.json.JsonKnowledgeLoader;
import model.ProjectKnowledge;
import org.junit.jupiter.api.Test;
import policy.RuntimePolicy;

import static org.junit.jupiter.api.Assertions.*;

class CircuitMindEngineTest {

    @Test
    void shouldAnalyzeTimeoutEndToEnd() {

        JsonKnowledgeLoader loader = new JsonKnowledgeLoader();
        ProjectKnowledge knowledge = loader.loadFromClasspath("knowledge-base.json");

        JsonKnowledgeBase knowledgeBase = new JsonKnowledgeBase(knowledge);

        CircuitMindEngine engine = new CircuitMindEngine(knowledgeBase);

        RuntimePolicy policy = engine.analyze(
                new RuntimeException("Read timed out"),
                "payment-service"
        );

        assertNotNull(policy);
        assertTrue(policy.isRetry());
        assertEquals(3, policy.getMaxAttempts());
        assertEquals("cached-response", policy.getFallback());
    }
}
