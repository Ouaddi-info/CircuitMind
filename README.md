# CircuitMind 🚀

AI-powered resilience engine for Java microservices.

## 🔥 Vision

Transform static resilience patterns into intelligent, adaptive systems.

---

## 🧠 What is CircuitMind?

CircuitMind is a Spring Boot starter that:
- analyzes runtime errors
- classifies failure patterns
- generates smart resilience decisions
- applies retry, circuit breaker, and fallback using Resilience4j

---

## ⚙️ Architecture

Exception
↓
Error Classifier
↓
Decision Engine
↓
Runtime Policy
↓
Resilience4j (Retry / Circuit Breaker)
↓
Fallback / Response


---

## 🚀 Quick Start

### 1. Add dependency

```xml
<dependency>
    <groupId>io.circuitmind</groupId>
    <artifactId>circuitmind-spring-boot-starter</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>

## Configure

circuitmind:
  enabled: true
  knowledge-base-path: knowledge-base.json

## Use it
@AiProtected(service = "payment-service")
public String process() {
    throw new RuntimeException("Read timed out");
}

📊 Features

AI-driven error classification
Dynamic retry & fallback
Circuit breaker integration (Resilience4j)
AOP-based interception
Metrics & observability


---

# 🧪 5. Demo Endpoint (مهم جدًا)

في `demo-order-service`:

```java
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/fail")
    @AiProtected(service = "payment-service")
    public String fail() {
        throw new RuntimeException("Read timed out");
    }
}

👨‍💻 Author

Mustapha Ouaddi
Senior Java Fullstack | AI Systems



