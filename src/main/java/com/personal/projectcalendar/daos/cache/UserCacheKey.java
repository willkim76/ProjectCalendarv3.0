package com.personal.projectcalendar.daos.cache;

public class UserCacheKey {
    private final String userName;

    public UserCacheKey(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}
