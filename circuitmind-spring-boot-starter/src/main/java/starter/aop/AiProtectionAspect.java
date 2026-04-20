package starter.aop;

import io.circuitmind.engine.CircuitMindEngine;
import io.circuitmind.core.policy.RuntimePolicy;
import io.circuitmind.starter.annotation.AiProtected;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AiProtectionAspect {

    private final CircuitMindEngine engine;

    public AiProtectionAspect(CircuitMindEngine engine) {
        this.engine = engine;
    }

    @Around("@annotation(aiProtected)")
    public Object protect(ProceedingJoinPoint joinPoint, AiProtected aiProtected) throws Throwable {
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
                    } catch (Exception retryException) {
                        // retry again
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
