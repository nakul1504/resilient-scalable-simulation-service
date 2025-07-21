package com.simulation.service.controller;

import com.simulation.service.service.TrafficGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TrafficTriggerController {

    private final TrafficGeneratorService trafficGeneratorService;

    public TrafficTriggerController(TrafficGeneratorService trafficGeneratorService) {
        this.trafficGeneratorService = trafficGeneratorService;
    }

    @PostMapping("/test/generate/traffic")
    public ResponseEntity<?> triggerLoad(
            @RequestParam(defaultValue = "http://localhost:8080/api/v1/process") String targetUrl,
            @RequestParam(defaultValue = "50") int threads,
            @RequestParam(defaultValue = "200") int requestsPerThread
    ) {
        trafficGeneratorService.generateLoad(targetUrl, threads, requestsPerThread);
        return ResponseEntity.accepted().body(Map.of("message","✅ Traffic generation started asynchronously."));
    }
}
