package com.ssd.javapotpouri.service;

import com.ssd.javapotpouri.data.BatchResult;
import com.ssd.javapotpouri.data.Todo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TodoBatchPartitionService {

    /**
     * Create batches of specific size
     */
    public List<List<Todo>> createBatches(List<Todo> todos, int batchSize) {
        AtomicInteger counter = new AtomicInteger(0);

        return todos.stream()
                .collect(Collectors.groupingBy(
                        todo -> counter.getAndIncrement() / batchSize
                ))
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    /**
     * Process with sliding window partition
     */
    public List<List<Todo>> slidingWindowPartition(List<Todo> todos, int windowSize, int step) {
        List<List<Todo>> windows = new ArrayList<>();

        for (int i = 0; i <= todos.size() - windowSize; i += step) {
            List<Todo> window = todos.subList(i, Math.min(i + windowSize, todos.size()));
            windows.add(window);
        }

        return windows;
    }

    /**
     * Process batches with rate limiting
     */
    public List<BatchResult> processBatchWithRateLimit(List<Todo> todos, int batchSize, long delayMs) {
        List<List<Todo>> batches = createBatches(todos, batchSize);
        List<BatchResult> results = new ArrayList<>();

        for (int i = 0; i < batches.size(); i++) {
            List<Todo> batch = batches.get(i);

            try {
                // Simulate rate limiting
                if (i > 0) {
                    Thread.sleep(delayMs);
                }

                BatchResult result = processBatch(batch, i);
                results.add(result);

                log.info("Processed batch {}: {} items", i, batch.size());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Batch processing interrupted", e);
            }
        }

        return results;
    }

    private BatchResult processBatch(List<Todo> batch, int batchNumber) {
        BatchResult result = new BatchResult();
        result.setBatchNumber(batchNumber);
        result.setOriginalSize(batch.size());

        List<Todo> processed = batch.stream()
                .map(todo -> {
                    todo.setTitle("[Batch: " + batchNumber + "] " + todo.getTitle().toUpperCase());
                    return todo;
                })
                .collect(Collectors.toList());

        result.setProcessedTodos(processed);
        result.setProcessedCount(processed.size());
        result.setStatus("SUCCESS");

        return result;
    }
}