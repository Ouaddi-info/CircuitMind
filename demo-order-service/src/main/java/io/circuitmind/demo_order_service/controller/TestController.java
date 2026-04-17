package io.circuitmind.demo_order_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private final CircuitMindEngine engine;

    public TestController() {
        JsonKnowledgeLoader loader = new JsonKnowledgeLoader();
        ProjectKnowledge knowledge = loader.loadFromClasspath("knowledge-base.json");

        this.engine = new CircuitMindEngine(new JsonKnowledgeBase(knowledge));
    }

    @GetMapping("/error")
    public String simulateError() {
        try {
            throw new RuntimeException("Read timed out");
        } catch (Exception e) {
            var policy = engine.analyze(e, "payment-service");

            return "Retry: " + policy.isRetry() +
                    ", Attempts: " + policy.getMaxAttempts() +
                    ", Fallback: " + policy.getFallback();
        }
    }
}
