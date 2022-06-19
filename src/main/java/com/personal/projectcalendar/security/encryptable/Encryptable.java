package com.personal.projectcalendar.security.encryptable;

public interface Encryptable {

    public String encryptMessage(String msgToEncrypt, String secret);

    public String decryptMessage(String msgToDecrypt, String secret);
}
