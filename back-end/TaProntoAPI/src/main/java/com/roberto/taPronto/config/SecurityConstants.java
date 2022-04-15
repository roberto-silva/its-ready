package com.roberto.taPronto.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityConstants {
    /*    Profiles*/
    public static final String TEST = "TEST";

    public static final String[] PUBLIC_MATCHERS = {

    };
    public static final String[] PUBLIC_TEST_MATCHERS = {
            "/h2-console/**"
    };
}
