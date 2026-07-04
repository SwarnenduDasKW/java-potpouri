package com.ssd.javapotpouri.client;

import com.ssd.javapotpouri.data.Todo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class TodoClient {

    private final WebClient webClient;

    public TodoClient(@Value("${todo.api.url}") String todoApiUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(todoApiUrl)
                .build();
    }

    public List<Todo> fetchTodos() {
        return webClient.get()
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        response -> Mono.error(new RuntimeException("Failed to fetch todos"))
                )
                .bodyToMono(new ParameterizedTypeReference<List<Todo>>() {})
                .block();
    }

    public Todo fetchTodoById(Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(
                        status -> status.isError(),
                        response -> Mono.error(new RuntimeException("Failed to fetch todo with id: " + id))
                )
                .bodyToMono(Todo.class)
                .block();
    }
}
