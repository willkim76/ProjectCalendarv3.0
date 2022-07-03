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
 * TODO
 */
public class UserUtilities {

    public static String generateUserId() {
        return String.format("user:%s", UUID.randomUUID());
    }

    public static boolean verifyUsername() {
        return false;
    }

    public static boolean verifyPassword() {
        return false;
    }
}
