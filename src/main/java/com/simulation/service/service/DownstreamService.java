package com.simulation.service.service;

import com.simulation.service.util.RoundRobinLoadBalancer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DownstreamService {
    private final RoundRobinLoadBalancer loadBalancer;

    public DownstreamService(RoundRobinLoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }

    @Async("taskExecutor")
    @CircuitBreaker(name = "downstreamCircuit", fallbackMethod = "fallback")
    public CompletableFuture<String> callExternalService() {
        SimulatorInstance instance = loadBalancer.getNextInstance();
        return CompletableFuture.completedFuture(instance.simulate());
    }

    public CompletableFuture<String> fallback(Throwable t) {
        return CompletableFuture.completedFuture("Fallback response: " + t.getMessage());
    }
}

