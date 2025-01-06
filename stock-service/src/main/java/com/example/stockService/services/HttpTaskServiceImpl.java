package com.example.stockService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HttpTaskServiceImpl implements HttpTaskService {

    private final WebClient webClient;

    public HttpTaskServiceImpl(@Autowired WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Void> toPdf() {
        System.out.println("from toPDF");
        return webClient.post()
                .uri("/report/api/v1/excelToPdf")
                .retrieve()
                .bodyToMono(Void.class); // Ожидаем пустой ответ
    }


}
