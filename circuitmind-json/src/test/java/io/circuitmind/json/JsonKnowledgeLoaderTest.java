package io.circuitmind.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonKnowledgeLoaderTest {

    @Test
    void shouldLoadKnowledgeBaseFromClasspath() {
        JsonKnowledgeLoader loader = new JsonKnowledgeLoader();

        ProjectKnowledge knowledge = loader.loadFromClasspath("knowledge-base.json");

        assertNotNull(knowledge);
        assertNotNull(knowledge.getErrorPatterns());
        assertFalse(knowledge.getErrorPatterns().isEmpty());
    }
}
