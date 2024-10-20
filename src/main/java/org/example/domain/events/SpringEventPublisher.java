package org.example.domain.events;

import lombok.RequiredArgsConstructor;
import org.example.domain.ports.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SpringEventPublisher implements EventPublisher<CarCreateEvent> {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(CarCreateEvent event) {
        applicationEventPublisher.publishEvent(event);
        // Events are stored in the Event Store now
    }
}