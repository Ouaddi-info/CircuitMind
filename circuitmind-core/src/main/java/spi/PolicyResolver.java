package spi;

import decision.ErrorDecision;
import policy.RuntimePolicy;

public interface PolicyResolver {
    RuntimePolicy resolve(ErrorDecision decision);
}
