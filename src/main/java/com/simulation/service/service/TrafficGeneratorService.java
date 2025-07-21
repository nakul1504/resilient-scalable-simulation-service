package com.simulation.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TrafficGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(TrafficGeneratorService.class);

    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public void generateLoad(String targetUrl, int threads, int requestsPerThread) {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        logger.info("Starting traffic generation: threads={}, requestsPerThread={}, target={}", threads, requestsPerThread, targetUrl);

        for (int i = 0; i < threads; i++) {
            int threadId = i;
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                for (int j = 0; j < requestsPerThread; j++) {
                    try {
                        String timestamp = LocalDateTime.now().toString();
                        logger.debug("[{}] [Thread-{}: {}] Sending request #{} to {}", timestamp, threadId, threadName, j + 1, targetUrl);

                        String response = restTemplate.getForObject(targetUrl, String.class);

                        logger.debug("[{}] [Thread-{}: {}] Received response #{}: {}", timestamp, threadId, threadName, j + 1, response);

                        Thread.sleep(200);
                    } catch (Exception e) {
                        String errTime = LocalDateTime.now().toString();
                        logger.error("[{}] [Thread-{}: {}] Error on request #{}: {}", errTime, threadId, threadName, j + 1, e.getMessage());
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                            logger.warn("Thread interrupted: {}", threadName);
                        }
                    }
                }
            });
        }

        executor.shutdown();
        logger.info("Traffic generation submitted to executor ({} threads).", threads);
    }
}