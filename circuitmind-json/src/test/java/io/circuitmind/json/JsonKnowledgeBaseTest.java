package io.circuitmind.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonKnowledgeBaseTest {

    @Test
    void shouldMatchTimeoutError() {
        JsonKnowledgeLoader loader = new JsonKnowledgeLoader();
        ProjectKnowledge knowledge = loader.loadFromClasspath("knowledge-base.json");

        JsonKnowledgeBase knowledgeBase = new JsonKnowledgeBase(knowledge);

        RuntimeException error = new RuntimeException("Read timed out");

        var match = knowledgeBase.findMatch(error, "payment-service");

        assertTrue(match.isPresent());
        assertEquals("TIMEOUT", match.get().getCategory());
    }
}
