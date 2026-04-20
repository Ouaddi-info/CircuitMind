package starter.config;


import io.circuitmind.json.JsonKnowledgeBase;
import io.circuitmind.json.JsonKnowledgeLoader;
import model.ProjectKnowledge;
import org.springframework.context.annotation.Bean;
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
}
