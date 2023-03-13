package com.roberto.taPronto.domain.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
public enum Status {

    NOT_STARTED(0, "Not started"),
    STARTED(1, "Started"),
    FINISHED(2, "Finished");

    private final Integer cod;
    private final String name;

    public Integer getCod() {
        return this.cod;
    }

    public String getName() {
        return this.name;
    }

    public static Status toEnum(Integer cod) {
        return Arrays.stream(Status.values()).filter(profile -> Objects.equals(profile.getCod(), cod))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Status not found."));
    }
}
