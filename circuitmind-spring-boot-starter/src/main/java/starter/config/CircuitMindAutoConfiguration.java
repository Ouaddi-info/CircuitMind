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



@Configuration
public class CircuitMindAutoConfiguration {

    @Bean
    public CircuitMindEngine circuitMindEngine() {
        JsonKnowledgeLoader loader = new JsonKnowledgeLoader();
        ProjectKnowledge knowledge = loader.loadFromClasspath("knowledge-base.json");

        return new CircuitMindEngine(new JsonKnowledgeBase(knowledge));
    }

    @Bean
    public AiProtectionAspect aiProtectionAspect(CircuitMindEngine engine) {
        return new AiProtectionAspect(engine);
    }

    @Bean
    public CircuitMindMetrics metrics(MeterRegistry registry) {
        return new CircuitMindMetrics(registry);
    }

    @Bean
    public CircuitMindLogger logger() {
        return new CircuitMindLogger();
    }
}
