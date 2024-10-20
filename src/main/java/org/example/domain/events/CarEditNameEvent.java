package org.example.domain.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@RequiredArgsConstructor
public class CarEditNameEvent {
    private final ZonedDateTime zonedDateTime = ZonedDateTime.now();

    private final Long carId;
    private final String name;
}