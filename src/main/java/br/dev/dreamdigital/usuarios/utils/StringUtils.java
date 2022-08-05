package br.dev.dreamdigital.usuarios.utils;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class StringUtils {

    private StringUtils() {
    }

    public static String toSha256(String message) {
        return Hashing.sha256()
                .hashString(message, StandardCharsets.UTF_8)
                .toString();
    }
}
