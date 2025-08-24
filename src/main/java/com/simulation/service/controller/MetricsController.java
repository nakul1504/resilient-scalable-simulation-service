package com.simulation.service.controller;

import com.simulation.service.service.LoadMetricsTrackerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/metrics")
@Tag(name = "Metrics APIs", description = "APIs for measuring load on the system")
public class MetricsController {

    private final LoadMetricsTrackerService tracker;

    public MetricsController(LoadMetricsTrackerService tracker) {
        this.tracker = tracker;
    }

    @GetMapping("/load")
    @Operation(summary = "Get the current load of success and failure request counts")
    public Map<String, Object> getLoad() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("success", tracker.getSuccessCounts());
        metrics.put("failure", tracker.getFailureCounts());
        return metrics;
    }
}
