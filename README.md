# Scalable and Resilient Microservice

This project demonstrates a scalable and resilient microservice built using **Spring Boot** with **Resilience4j**, **custom load balancing**, **horizontal scaling via async/thread pools**, and a built-in **traffic load generator**.

---

## ✅ Features Implemented

### 🔁 Resilience Pattern: Circuit Breaker (Resilience4j)
- Protects the system from cascading failures.
- Uses Resilience4j's circuit breaker with fallback response handling.
- Configuration allows real-time state transitions (`CLOSED`, `OPEN`, `HALF_OPEN`).

### 📈 Scalability Techniques
1. **Horizontal Scaling**
    - Implemented using `@Async` with Spring's thread pool to process requests concurrently.

2. **Custom Load Balancing**
    - A custom round-robin load balancer distributes calls across multiple downstream service instances (simulated as different beans).
    - Simulates load distribution like real-world services behind a load balancer.

### 🚦 Failure Simulation
- Two simulator services (`First Instance`, `Second Instance`) randomly throw exceptions to simulate intermittent failures.
- Circuit breaker reacts and routes to fallback accordingly.

### 📊 Monitoring / Load Dashboard
- `/metrics/load` endpoint shows success and failure counts per instance.
- Useful for visualizing traffic distribution and failures.

### 🚀 Load Generator
- Built-in load generator triggered via a REST API.
- Runs asynchronously and stresses the `/process` endpoint to simulate heavy traffic.

---

## 🔧 How to Run

### ✅ 1. Clone and Build
```bash
git clone https://github.com/your-username/scalable-resilient-microservice.git
cd scalable-resilient-microservice
./mvnw clean install
```

### ✅ 2. Run the Application
```bash
./mvnw spring-boot:run
```

### ✅ 3. Test Traffic Load
Send a POST request to trigger the built-in load generator:
```bash
curl -X POST "http://localhost:8080/api/v1/test/generate"
```

You can also customize:
```bash
curl -X POST "http://localhost:8080/api/v1/test/generate?threads=20&requestsPerThread=300"
```

### ✅ 4. Monitor Load Per Instance
```bash
curl http://localhost:8080/api/v1/metrics/load
```

### ✅ 5. Monitor Circuit Breaker Events
Enable actuator in `application.yml` and visit:
```
http://localhost:8080/actuator/circuitbreakerevents
```

---

## ⚙️ Configuration Summary

### `application.yml`
```yaml
resilience4j:
  circuitbreaker:
    instances:
      downstreamCircuit:
        slidingWindowType: COUNT_BASED
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        failureRateThreshold: 50
        waitDurationInOpenState: 10s
        permittedNumberOfCallsInHalfOpenState: 2

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,circuitbreakers
logging:
  level:
    io.github.resilience4j.circuitbreaker: DEBUG
```

---

## 📦 Project Structure

```
├── SimulationServiceApplication.java
├── controller
│   ├── SimulationController.java
│   └── TrafficTriggerController.java
│   └── MetricsController.java
├── service
│   ├── impl
        └──DummySimulatorInstanceA.java
        └──DummySimulatorInstanceB.java
│   ├── DownstreamService.java
│   ├──SimulatorInstance
│   ├── LoadMetricsTracker.java
│   └── TrafficGeneratorService.java
├── util
│   └── RoundRobinLoadBalancer.java
└── resources
    └── application.yml
```

---

## ✅ Summary
This project demonstrates how to:
- Scale a microservice using async/thread pool processing.
- Balance load across multiple instances.
- Handle failures gracefully with a circuit breaker.
- Simulate and test real-world traffic and failures.
- Monitor system load and response behavior easily.