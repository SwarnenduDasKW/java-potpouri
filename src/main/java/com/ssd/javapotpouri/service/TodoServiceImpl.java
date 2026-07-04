package com.ssd.javapotpouri.service;

import com.ssd.javapotpouri.client.TodoClient;
import com.ssd.javapotpouri.data.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoClient todoClient;

    @Override
    public List<Todo> getAllTodos() {
        return todoClient.fetchTodos();
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoClient.fetchTodoById(id);
    }
}