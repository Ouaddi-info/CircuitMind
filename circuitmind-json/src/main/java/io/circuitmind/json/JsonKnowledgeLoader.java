package io.circuitmind.json;

import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonKnowledgeLoader {
    private final ObjectMapper objectMapper;

    public JsonKnowledgeLoader() {
        this.objectMapper = new ObjectMapper();
    }

    public ProjectKnowledge loadFromClasspath(String resourcePath) {
        InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(resourcePath);

        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        try {
            return objectMapper.readValue(inputStream, ProjectKnowledge.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load knowledge base from: " + resourcePath, e);
        }
    }

    private void validate(ProjectKnowledge knowledge) {
        if (knowledge == null) {
            throw new KnowledgeValidationException("Knowledge base is null");
        }
        if (knowledge.getErrorPatterns() == null || knowledge.getErrorPatterns().isEmpty()) {
            throw new KnowledgeValidationException("Knowledge base must contain at least one error pattern");
        }
    }
}
