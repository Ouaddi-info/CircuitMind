package spi;

import model.ErrorPatternDefinition;

import java.util.Optional;

public interface KnowledgeBase {
    Optional<ErrorPatternDefinition> findMatch(Throwable error, String serviceName);
}
