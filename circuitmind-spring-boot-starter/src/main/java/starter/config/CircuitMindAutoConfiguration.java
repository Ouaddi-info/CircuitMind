package starter.config;


import engine.CircuitMindEngine;
import io.circuitmind.json.JsonKnowledgeBase;
import io.circuitmind.json.JsonKnowledgeLoader;
import io.circuitmind.observability.CircuitMindLogger;
import io.circuitmind.observability.CircuitMindMetrics;
import io.micrometer.core.instrument.MeterRegistry;
import model.ProjectKnowledge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import starter.aop.AiProtectionAspect;
import starter.resilience.ResilienceExecutor;


@Configuration
public class CircuitMindAutoConfiguration {

    @Bean
    public CircuitMindEngine circuitMindEngine() {
        JsonKnowledgeLoader loader = new JsonKnowledgeLoader();
        ProjectKnowledge knowledge = loader.loadFromClasspath("knowledge-base.json");

        return new CircuitMindEngine(new JsonKnowledgeBase(knowledge));
    }

    @Bean
    public AiProtectionAspect aiProtectionAspect(
            CircuitMindEngine engine,
            CircuitMindMetrics metrics,
            CircuitMindLogger logger,
            ResilienceExecutor resilienceExecutor) {

        return new AiProtectionAspect(engine, metrics, logger, resilienceExecutor);
    }

    @Bean
    public CircuitMindMetrics metrics(MeterRegistry registry) {
        return new CircuitMindMetrics(registry);
    }

    @Bean
    public CircuitMindLogger logger() {
        return new CircuitMindLogger();
    }

    @Bean
    public ResilienceExecutor resilienceExecutor() {
        return new ResilienceExecutor();
    }
}
