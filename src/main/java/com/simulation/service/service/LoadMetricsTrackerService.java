package com.simulation.service.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class LoadMetricsTrackerService {

    private final ConcurrentMap<String, AtomicInteger> successCounts = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, AtomicInteger> failureCounts = new ConcurrentHashMap<>();

    public void recordSuccess(String instanceName) {
        successCounts.computeIfAbsent(instanceName, key -> new AtomicInteger(0)).incrementAndGet();
    }

    public void recordFailure(String instanceName) {
        failureCounts.computeIfAbsent(instanceName, key -> new AtomicInteger(0)).incrementAndGet();
    }

    public Map<String, Integer> getSuccessCounts() {
        return successCounts.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get()));
    }

    public Map<String, Integer> getFailureCounts() {
        return failureCounts.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get()));
    }
}
