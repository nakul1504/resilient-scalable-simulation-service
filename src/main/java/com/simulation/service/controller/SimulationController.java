package com.simulation.service.controller;

import com.simulation.service.service.DownstreamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1")
public class SimulationController {

    private final DownstreamService downstreamService;

    public SimulationController(DownstreamService downstreamService) {
        this.downstreamService = downstreamService;
    }

    @GetMapping("/process")
    public CompletableFuture<String> process() {
        return downstreamService.callExternalService();
    }

}
