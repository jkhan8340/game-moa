package com.game.moa.util;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Base64UtilsTest {

    private static final String TEST_STRING = "TEST";

    @Test
    public void testBase64() {
        String base64EncodingString = Base64Utils.encodedString(TEST_STRING);
        assertThat(base64EncodingString).isBase64();
        assertThat(Base64Utils.decodedString(base64EncodingString)).isEqualTo(TEST_STRING);
    }

}