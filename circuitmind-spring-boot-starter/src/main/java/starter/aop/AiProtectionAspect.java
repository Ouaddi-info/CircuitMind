package starter.aop;


import engine.CircuitMindEngine;
import io.circuitmind.observability.CircuitMindLogger;
import io.circuitmind.observability.CircuitMindMetrics;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import policy.RuntimePolicy;
import starter.annotation.AiProtected;
import starter.config.CircuitMindProperties;
import starter.resilience.ResilienceExecutor;

@Aspect
public class AiProtectionAspect {

    private final CircuitMindEngine engine;

    private final CircuitMindMetrics metrics;
    private final CircuitMindLogger logger;
    private final ResilienceExecutor resilienceExecutor;
    private final CircuitMindProperties properties;



    public AiProtectionAspect(CircuitMindEngine engine,
                              CircuitMindMetrics metrics,
                              CircuitMindLogger logger,
                              ResilienceExecutor resilienceExecutor,
                              CircuitMindProperties properties) {
        this.engine = engine;
        this.metrics = metrics;
        this.logger = logger;
        this.resilienceExecutor = resilienceExecutor;
        this.properties = properties;
    }

    @Around("@annotation(aiProtected)")
    public Object protect(ProceedingJoinPoint joinPoint, AiProtected aiProtected) throws Throwable {
        if (!properties.isEnabled()) {
            return joinPoint.proceed();
        }
        try {
            return joinPoint.proceed();
        } catch (Exception e) {

            RuntimePolicy policy = engine.analyze(e, aiProtected.service());

            if (policy.isRetry()) {
                int attempts = policy.getMaxAttempts();

                for (int i = 0; i < attempts; i++) {
                    try {
                        Thread.sleep(policy.getBackoffMs());
                        return joinPoint.proceed();
                    } catch (Exception executionException) {
                        if (policy.getFallback() != null) {
                            metrics.incrementFallback();
                            return "[Fallback] " + policy.getFallback();
                        }
                        throw executionException;
                    }
                }
            }

            if (policy.getFallback() != null) {
                return "[Fallback] " + policy.getFallback();
            }

            throw e;

        }
    }
}
