package com.personal.projectcalendar.utilities;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.UUID;

/**
 *
 */
public class UserUtilities {
    private static final int HASH_ITERATION_COUNT   = 65536;
    private static final int HASH_KEY_LENGTH        = 128;

    public static String generateUserId() {
        return String.format("user:%s", UUID.randomUUID());
    }

    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        return new String(salt);
    }

    public static String generatePBKDF2Hash(final String password, final String salt) {

        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(),
                    salt.getBytes(StandardCharsets.UTF_8),
                    HASH_ITERATION_COUNT,
                    HASH_KEY_LENGTH);

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            return new String(factory.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
