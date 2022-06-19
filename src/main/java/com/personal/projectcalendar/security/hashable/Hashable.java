package com.personal.projectcalendar.security.hashable;

public interface Hashable {

    public String generateHash(String password, String salt);

    public String generateSalt();
}
