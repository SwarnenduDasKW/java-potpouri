package com.ssd.javapotpouri.service;

import com.ssd.javapotpouri.data.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    Todo getTodoById(Long id);
}