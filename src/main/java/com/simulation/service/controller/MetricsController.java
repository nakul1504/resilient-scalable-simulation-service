package com.simulation.service.controller;

import com.simulation.service.service.LoadMetricsTrackerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/metrics")
public class MetricsController {

    private final LoadMetricsTrackerService tracker;

    public MetricsController(LoadMetricsTrackerService tracker) {
        this.tracker = tracker;
    }

    @GetMapping("/load")
    public Map<String, Object> getLoad() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("success", tracker.getSuccessCounts());
        metrics.put("failure", tracker.getFailureCounts());
        return metrics;
    }
}
