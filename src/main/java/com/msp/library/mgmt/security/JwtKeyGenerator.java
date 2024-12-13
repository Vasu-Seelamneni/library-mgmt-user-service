package com.msp.library.mgmt.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Generates a 256-bit key
        System.out.println(java.util.Base64.getEncoder().encodeToString(key.getEncoded()));
    }
}
