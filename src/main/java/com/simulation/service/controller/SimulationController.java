package com.simulation.service.controller;

import com.simulation.service.service.DownstreamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Simulation APIs", description = "APIs to simulate external service api calls")
public class SimulationController {

    private final DownstreamService downstreamService;

    public SimulationController(DownstreamService downstreamService) {
        this.downstreamService = downstreamService;
    }

    @GetMapping("/process")
    @Operation(summary = "Simulate call to an external service")
    public CompletableFuture<String> process() {
        return downstreamService.callExternalService();
    }

}
