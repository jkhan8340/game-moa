package com.game.moa.util;

import java.util.Base64;

public class Base64Utils {

    public static String encodedString(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static String decodedString(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

}
