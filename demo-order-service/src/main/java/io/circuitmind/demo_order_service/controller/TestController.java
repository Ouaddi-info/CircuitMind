package io.circuitmind.demo_order_service.controller;

import engine.CircuitMindEngine;
import io.circuitmind.json.JsonKnowledgeBase;
import io.circuitmind.json.JsonKnowledgeLoader;
import model.ProjectKnowledge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.circuitmind.*;
import starter.annotation.AiProtected;

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
    @AiProtected(service = "payment-service")
    public String simulateError() {
        throw new RuntimeException("Read timed out");
    }
}
