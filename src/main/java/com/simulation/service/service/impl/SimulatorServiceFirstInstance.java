package com.simulation.service.service.impl;

import com.simulation.service.service.LoadMetricsTrackerService;
import com.simulation.service.service.SimulatorInstance;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("firstInstance")
public class SimulatorServiceFirstInstance implements SimulatorInstance {
    private final Random random = new Random();
    private final LoadMetricsTrackerService tracker;

    public SimulatorServiceFirstInstance(LoadMetricsTrackerService tracker) {
        this.tracker = tracker;
    }

    @Override
    public String simulate() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        if (random.nextInt(5) < 2) {
            tracker.recordFailure("First Instance");
            throw new RuntimeException("First instance simulated failure");
        }

        tracker.recordSuccess("First Instance");
        return "Response from Instance First";
    }
}
