package com.kamruddin.studentclient.components;

import com.kamruddin.studentclient.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class StudentClient {

    private final WebClient webClient;
    public Student getStudent(Long id) {
        return webClient.get()
                .uri("/students/{id}", id)
                .retrieve()
                .bodyToMono(Student.class)
                .block();
    }
}
