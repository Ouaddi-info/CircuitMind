package io.circuitmind.json;

import model.ErrorPatternDefinition;
import model.ProjectKnowledge;
import spi.KnowledgeBase;

import java.util.Optional;

public class JsonKnowledgeBase implements KnowledgeBase {

    private final ProjectKnowledge knowledge;

    public JsonKnowledgeBase(ProjectKnowledge knowledge) {
        this.knowledge = knowledge;
    }

    @Override
    public Optional<ErrorPatternDefinition> findMatch(Throwable error, String serviceName) {
        String message = error.getMessage();

        return knowledge.getErrorPatterns()
                .stream()
                .filter(pattern ->
                        pattern.getMatchers() != null &&
                                pattern.getMatchers().stream()
                                        .anyMatch(matcher ->
                                                message != null && message.contains(matcher)
                                        )
                )
                .findFirst();
    }
}
