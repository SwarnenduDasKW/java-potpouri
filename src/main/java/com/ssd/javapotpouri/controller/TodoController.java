package com.ssd.javapotpouri.controller;

import com.ssd.javapotpouri.data.BatchResult;
import com.ssd.javapotpouri.data.Todo;
import com.ssd.javapotpouri.service.TodoBatchPartitionService;
import com.ssd.javapotpouri.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final TodoBatchPartitionService todoBatchPartitionService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos() {
        try {
            List<Todo> todos = todoService.getAllTodos();
            return ResponseEntity.ok(todos);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        try {
            Todo todo = todoService.getTodoById(id);
            if (todo != null) {
                return ResponseEntity.ok(todo);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/batch/{chunk}")
    public ResponseEntity<List<BatchResult>> processTodoInBatch(@PathVariable int chunk) {
        try {
            List<Todo> todos = todoService.getAllTodos();

            List<BatchResult> batchResults = new ArrayList<>();
            batchResults = todoBatchPartitionService.processBatchWithRateLimit(todos,chunk,10);
            return ResponseEntity.ok(batchResults);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
