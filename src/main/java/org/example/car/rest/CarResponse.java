package org.example.car.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Getter
@ToString(of = {"uuid", "name", "owner", "amount", "warrantyDate"})
public class CarResponse {
    private UUID uuid;
    private int version;
    private String name;
    private String owner;
    private long amount;
    private ZonedDateTime warrantyDate;
}
