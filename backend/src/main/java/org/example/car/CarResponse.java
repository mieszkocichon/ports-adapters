package org.example.car;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Getter
@ToString(of = {"uuid", "carId", "name", "owner", "amount", "warrantyDate"})
@EqualsAndHashCode(of = "carId")
public class CarResponse {
    private UUID uuid;
    private UUID carId;
    private int version;
    private String name;
    private String owner;
    private long amount;
    private ZonedDateTime warrantyDate;
}
