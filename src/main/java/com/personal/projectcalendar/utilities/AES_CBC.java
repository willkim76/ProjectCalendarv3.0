package com.personal.projectcalendar.utilities;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * A utility class for encrypting and decrypting Strings.
 * TODO: This is a vulnerable encryption! Needs to be reimplemented with a
 *          GSM strategy. Examples below:
 *          https://mkyong.com/java/java-aes-encryption-and-decryption/
 *          https://www.javainterviewpoint.com/java-aes-256-gcm-encryption-and-decryption/
 */
public final class AES_CBC {
    private static final String ENCRYPT_ALGO    = "AES/ECB/PKCS5Padding";
    private static final String HASH_FUNCTION   = "SHA-1";
    private static final String ALGO_FUNCTION   = "AES";
    private static final Charset CHARSET        = StandardCharsets.UTF_8;

    /**
     * Do not Instantiate.
     */
    private AES_CBC() {}

    /**
     * Utility method that encrypts a String.
     * @param msgToEncrypt String to encrypt.
     * @param secret String to encrypt with.
     * @return The encrypted String.
     */
    public static String encryptMessage(final String msgToEncrypt, final String secret) {
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, AES_CBC.fetchKey(secret));

            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(msgToEncrypt.getBytes(CHARSET)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to encrypt!");
        }
    }

    /**
     * Utility method that decrypts a String.
     * @param msgToDecrypt String to decrypt
     * @param secret String to decrypt with.
     * @return The decrypted String.
     */
    public static String decryptMessage(final String msgToDecrypt, final String secret) {
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
            cipher.init(Cipher.DECRYPT_MODE, AES_CBC.fetchKey(secret));

            return new String(cipher.doFinal(Base64.getDecoder().decode(msgToDecrypt)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to decrypt!");
        }
    }

    /**
     * Generates a secrete key spec for encryption and decryption.
     * @param key String to generate a key.
     */
    private static SecretKeySpec fetchKey(final String key)
            throws NoSuchAlgorithmException {
        MessageDigest sha;
        byte[] byteKey;

        byteKey = key.getBytes(CHARSET);
        sha = MessageDigest.getInstance(HASH_FUNCTION);
        byteKey = sha.digest(byteKey);
        byteKey = Arrays.copyOf(byteKey, 16);

        return new SecretKeySpec(byteKey, ALGO_FUNCTION);
    }
}
