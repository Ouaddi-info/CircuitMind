package engine;

import decision.ErrorDecision;
import policy.RuntimePolicy;
import spi.PolicyResolver;

public class DefaultPolicyResolver implements PolicyResolver {

    @Override
    public RuntimePolicy resolve(ErrorDecision decision) {
        if (decision == null) {
            return new RuntimePolicy(false, 0, 0, false, "default-fallback");
        }

        if ("UNKNOWN".equalsIgnoreCase(decision.getCategory())) {
            return new RuntimePolicy(false, 0, 0, false, "default-fallback");
        }

        if ("TIMEOUT".equalsIgnoreCase(decision.getCategory())) {
            return new RuntimePolicy(
                    decision.isShouldRetry(),
                    3,
                    500,
                    decision.isShouldOpenCircuit(),
                    decision.getFallback()
            );
        }

        if ("AUTH".equalsIgnoreCase(decision.getCategory())) {
            return new RuntimePolicy(
                    false,
                    0,
                    0,
                    false,
                    "deny-request"
            );
        }

        return new RuntimePolicy(
                decision.isShouldRetry(),
                1,
                200,
                decision.isShouldOpenCircuit(),
                decision.getFallback() != null ? decision.getFallback() : "default-fallback"
        );
    }
}
