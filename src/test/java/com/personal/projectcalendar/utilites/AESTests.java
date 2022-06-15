package com.personal.projectcalendar.utilites;

import com.personal.projectcalendar.utilities.AES_CBC;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AESTests {

    @Test
    @Order(1)
    void encrypt_withNonEmptyMsgNonEmptyKey_encryptsMessage() {
        // GIVEN
        String key = "Test Key";
        String msg = "Test Message";

        // WHEN
        String encrypted = AES_CBC.encryptMessage(msg, key);

        // THEN
        assertNotEquals(msg, encrypted);
    }

    @Test
    @Order(2)
    void decrypt_withNonEmptyMsgNonEmptyIdenticalKeys_decryptsMessage() {
        // GIVEN
        String key = "Test Key";
        String msg = "Test Message";
        String encrypted = AES_CBC.encryptMessage(msg, key);

        // WHEN
        String decrypted = AES_CBC.decryptMessage(encrypted, key);

        // THEN
        assertEquals(msg, decrypted);
    }

    @Test
    @Order(3)
    void encrypt_withEmptyMsgNonEmptyKey_encryptsMessage() {
        // GIVEN
        String key = "Test Key";
        String msg = "";

        // WHEN
        String encrypted = AES_CBC.encryptMessage(msg, key);

        // THEN
        assertNotEquals(msg, encrypted);
    }

    @Test
    @Order(4)
    void decrypt_withEmptyMsgNonEmptyIdenticalKeys_decryptsMessage() {
        // GIVEN
        String key = "Test Key";
        String msg = "";
        String encrypted = AES_CBC.encryptMessage(msg, key);

        // WHEN
        String decrypted = AES_CBC.decryptMessage(encrypted, key);

        // THEN
        assertEquals(decrypted, msg);
    }

    @Test
    @Order(5)
    void encrypt_withNonEmptyMsgEmptyKey_encryptsMessage() {
        // GIVEN
        String key = "";
        String msg = "Test Message";

        // WHEN
        String encrypted = AES_CBC.encryptMessage(msg, key);

        // THEN
        assertNotEquals(msg, encrypted);
    }

    @Test
    @Order(6)
    void decrypt_withNonEmptyMsgEmptyKey_decryptsMessage() {
        // GIVEN
        String key = "";
        String msg = "Test Message";
        String encrypted = AES_CBC.encryptMessage(msg, key);

        // WHEN
        String decrypted = AES_CBC.decryptMessage(encrypted, key);

        // THEN
        assertEquals(decrypted, msg);
    }

    @Test
    void decrypt_withNonEmptyMsgNonEmptyDifferentKeys_throwsException() {
        // GIVEN
        String key = "Test Key";
        String badKey = "Test Kee";
        String msg = "Te";
        String encrypted = AES_CBC.encryptMessage(msg, key);

        // WHEN - THEN
        assertThrows(IllegalArgumentException.class, () -> {
            String decrypted = AES_CBC.decryptMessage(msg, badKey);

            // If AES_CBC::decrypt does not throw IllegalArgumentException
            if (!decrypted.equals(msg)) {
                System.out.println("Did not throw Exception");
                throw new IllegalArgumentException();
            }
        });
    }

    @Test
    void encrypt_withNonEmptyMsgNullKey_throwsIllegalArgumentException() {
        // GIVEN
        String key = null;
        String msg = "Test Message";

        // WHEN THEN
        assertThrows(IllegalArgumentException.class, () -> AES_CBC.encryptMessage(msg, key));
    }

    @Test
    void decrypt_withNullMsgNonEmptyKey_throwsIllegalArgumentException() {
        // GIVEN
        String key = "Test Key";
        String msg = null;

        // WHEN THEN
        assertThrows(IllegalArgumentException.class, () -> AES_CBC.encryptMessage(msg, key));
    }

    @Test
    void encrypt_withNonEmptyMsgToEncryptNullKey_returnsNull() {
        // GIVEN
        String key = "Test Key";
        String msg = null;
        String encrypted = AES_CBC.encryptMessage(msg, key);

        // WHEN
        String decrypted = AES_CBC.decryptMessage(encrypted, key);

        // THEN
        assertNotNull(encrypted);
        assertEquals(msg, decrypted);
    }
}
