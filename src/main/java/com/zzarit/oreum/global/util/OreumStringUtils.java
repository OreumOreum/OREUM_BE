package com.zzarit.oreum.global.util;

import java.util.Arrays;

public class OreumStringUtils {

    public static String removePrefix(String fullAddr) {
        if(fullAddr == null) return null;

        String[] token = fullAddr.split(" ");
        return String.join(" ", Arrays.copyOfRange(token, 1, token.length));
    }
}
