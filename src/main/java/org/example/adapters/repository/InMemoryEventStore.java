package org.example.adapters.repository;

import org.example.domain.events.CarCreateEvent;
import org.example.domain.events.CarEditNameEvent;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryEventStore {

    private Map<Long, List<Object>> store = new HashMap<>();

    public void saveEvent(Object event) {
        Long aggregateId = getAggregateId(event);
        store.computeIfAbsent(aggregateId, k -> new ArrayList<>()).add(event);
    }

    public List<Object> getEventsForAggregate(Long aggregateId) {
        return store.getOrDefault(aggregateId, new ArrayList<>());
    }

    private Long getAggregateId(Object event) {
        if (event instanceof CarCreateEvent) {
            return ((CarCreateEvent) event).getCarId();
        }
        if (event instanceof CarEditNameEvent) {
            return ((CarEditNameEvent) event).getCarId();
        }
        throw new IllegalArgumentException("Unknown event type");
    }
}