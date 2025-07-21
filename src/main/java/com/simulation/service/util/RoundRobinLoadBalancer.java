package com.simulation.service.util;

import com.simulation.service.service.SimulatorInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RoundRobinLoadBalancer {
    private final List<SimulatorInstance> instances;
    private final AtomicInteger counter = new AtomicInteger(0);

    public RoundRobinLoadBalancer(List<SimulatorInstance> instances) {
        this.instances = instances;
    }

    public SimulatorInstance getNextInstance() {
        int index = Math.abs(counter.getAndIncrement() % instances.size());
        return instances.get(index);
    }
}
