import decision.ErrorDecision;
import org.junit.jupiter.api.Test;
import policy.RuntimePolicy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPolicyResolverTest {

    @Test
    void shouldResolveTimeoutPolicy() {
        ErrorDecision decision = new ErrorDecision(
                "payment-service",
                "TIMEOUT",
                "downstream latency",
                true,
                true,
                "cached-response",
                List.of("check downstream latency")
        );

        DefaultPolicyResolver resolver = new DefaultPolicyResolver();
        RuntimePolicy policy = resolver.resolve(decision);

        assertNotNull(policy);
        assertTrue(policy.isRetry());
        assertEquals(3, policy.getMaxAttempts());
        assertEquals(500, policy.getBackoffMs());
        assertTrue(policy.isOpenCircuit());
        assertEquals("cached-response", policy.getFallback());
    }

    @Test
    void shouldResolveUnknownPolicy() {
        ErrorDecision decision = new ErrorDecision(
                "payment-service",
                "UNKNOWN",
                "unknown",
                false,
                false,
                "default-fallback",
                List.of("inspect logs")
        );

        DefaultPolicyResolver resolver = new DefaultPolicyResolver();
        RuntimePolicy policy = resolver.resolve(decision);

        assertNotNull(policy);
        assertFalse(policy.isRetry());
        assertEquals(0, policy.getMaxAttempts());
        assertEquals("default-fallback", policy.getFallback());
    }
}
