package com.ssd.javapotpouri.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BatchResult {
    private int batchNumber;
    private int originalSize;
    private int processedCount;
    private String status;
    private List<Todo> processedTodos;

}
