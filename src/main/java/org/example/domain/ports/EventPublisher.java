package org.example.domain.ports;

public interface EventPublisher<T> {
    void publish(T event);
}