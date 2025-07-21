package com.simulation.service.service.impl;

import com.simulation.service.service.LoadMetricsTrackerService;
import com.simulation.service.service.SimulatorInstance;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("secondInstance")
public class SimulatorServiceSecondInstance implements SimulatorInstance {
    private final Random random = new Random();
    private final LoadMetricsTrackerService tracker;

    public SimulatorServiceSecondInstance(LoadMetricsTrackerService tracker) {
        this.tracker = tracker;
    }

    @Override
    public String simulate() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        if (random.nextInt(5) < 2) {
            tracker.recordFailure("Second Instance");
            throw new RuntimeException("Second instance simulated failure");
        }
        tracker.recordSuccess("Second Instance");
        return "Response from Second Instance ";
    }
}

