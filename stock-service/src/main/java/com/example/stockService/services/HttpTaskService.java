package com.example.stockService.services;

import reactor.core.publisher.Mono;

public interface HttpTaskService {
    Mono<Void> toPdf();
}
