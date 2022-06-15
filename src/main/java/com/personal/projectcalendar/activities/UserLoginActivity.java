package com.personal.projectcalendar.activities;

import com.personal.projectcalendar.daos.cache.UserDaoCache;
import com.personal.projectcalendar.types.requests.UserLoginRequest;
import com.personal.projectcalendar.types.responses.UserLoginResponse;

import javax.inject.Inject;

public class UserLoginActivity {
    private final UserDaoCache userDaoCache;

    @Inject
    public UserLoginActivity(UserDaoCache userDaoCache) {
        this.userDaoCache = userDaoCache;
    }

    public UserLoginResponse execute(UserLoginRequest request) {
        throw new IllegalArgumentException();
    }
}
