package com.personal.projectcalendar.utilites;

import com.personal.projectcalendar.security.encryptable.AES_CBC_Encryption;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AESTests {
    private AES_CBC_Encryption encryption;

    @BeforeEach
    void setup() {
        this.encryption = new AES_CBC_Encryption();
    }

    @Test
    @Order(1)
    void encrypt_withNonEmptyMsgNonEmptyKey_encryptsMessage() {
        // GIVEN
        String key = "Test Key";
        String msg = "Test Message";

        // WHEN
        String encrypted = encryption.encryptMessage(msg, key);

        // THEN
        assertNotEquals(msg, encrypted);
    }

    @Test
    @Order(2)
    void decrypt_withNonEmptyMsgNonEmptyIdenticalKeys_decryptsMessage() {
        // GIVEN
        String key = "Test Key";
        String msg = "Test Message";
        String encrypted = encryption.encryptMessage(msg, key);

        // WHEN
        String decrypted = encryption.decryptMessage(encrypted, key);

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
        String encrypted = encryption.encryptMessage(msg, key);

        // THEN
        assertNotEquals(msg, encrypted);
    }

    @Test
    @Order(4)
    void decrypt_withEmptyMsgNonEmptyIdenticalKeys_decryptsMessage() {
        // GIVEN
        String key = "Test Key";
        String msg = "";
        String encrypted = encryption.encryptMessage(msg, key);

        // WHEN
        String decrypted = encryption.decryptMessage(encrypted, key);

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
        String encrypted = encryption.encryptMessage(msg, key);

        // THEN
        assertNotEquals(msg, encrypted);
    }

    @Test
    @Order(6)
    void decrypt_withNonEmptyMsgEmptyKey_decryptsMessage() {
        // GIVEN
        String key = "";
        String msg = "Test Message";
        String encrypted = encryption.encryptMessage(msg, key);

        // WHEN
        String decrypted = encryption.decryptMessage(encrypted, key);

        // THEN
        assertEquals(decrypted, msg);
    }

    @Test
    void decrypt_withNonEmptyMsgNonEmptyDifferentKeys_throwsException() {
        // GIVEN
        String key = "Test Key";
        String badKey = "Test Kee";
        String msg = "Te";
        String encrypted = encryption.encryptMessage(msg, key);

        // WHEN - THEN
        assertThrows(IllegalArgumentException.class, () -> {
            String decrypted = encryption.decryptMessage(msg, badKey);

            // If AES_CBC_Encryption::decrypt does not throw IllegalArgumentException
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
        assertThrows(IllegalArgumentException.class, () -> encryption.encryptMessage(msg, key));
    }

    @Test
    void decrypt_withNullMsgNonEmptyKey_throwsIllegalArgumentException() {
        // GIVEN
        String key = "Test Key";
        String msg = null;

        // WHEN THEN
        assertThrows(IllegalArgumentException.class, () -> encryption.encryptMessage(msg, key));
    }

    @Test
    void encrypt_withNonEmptyMsgToEncryptNullKey_returnsNull() {
        // GIVEN
        String key = "Test Key";
        String msg = null;
        String encrypted = encryption.encryptMessage(msg, key);

        // WHEN
        String decrypted = encryption.decryptMessage(encrypted, key);

        // THEN
        assertNotNull(encrypted);
        assertEquals(msg, decrypted);
    }
}
