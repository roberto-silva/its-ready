package com.roberto.taPronto.domain.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
public enum Profile {

    ADMIN(0, "ROLE_ADMIN"),
    ATTENDANT(1, "ROLE_ATTENDANT"),
    COSTUMER(2, "ROLE_COSTUMER");

    private final Integer cod;
    private final String name;

    public Integer getCod() {
        return this.cod;
    }

    public String getName() {
        return this.name;
    }

    public static Profile toEnum(Integer cod) {
        return Arrays.stream(Profile.values()).filter(profile -> Objects.equals(profile.getCod(), cod))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Profile not found."));
    }
}
