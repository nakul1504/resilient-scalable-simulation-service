package com.simulation.service.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TrafficGeneratorService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Async
    public void generateLoad(String targetUrl, int threads, int requestsPerThread) {
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            int threadId = i;
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                for (int j = 0; j < requestsPerThread; j++) {
                    try {
                        String timestamp = LocalDateTime.now().toString();
                        System.out.printf(
                                "[%s] [Thread-%d: %s] Sending request #%d to %s%n",
                                timestamp, threadId, threadName, j + 1, targetUrl
                        );
                        String response = restTemplate.getForObject(targetUrl, String.class);
                        System.out.printf(
                                "[%s] [Thread-%d: %s] Received response #%d: %s%n",
                                timestamp, threadId, threadName, j + 1, response
                        );
                        Thread.sleep(200);
                    } catch (Exception e) {
                        String errTime = LocalDateTime.now().toString();
                        System.err.printf(
                                "[%s] [Thread-%d: %s] Error on request #%d: %s%n",
                                errTime, threadId, threadName, j + 1, e.getMessage()
                        );
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException ie) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            });
        }

        executor.shutdown();
    }
}
