package com.personal.projectcalendar.activities;

import com.personal.projectcalendar.converters.models.UserModelConverter;
import com.personal.projectcalendar.daos.UserDao;
import com.personal.projectcalendar.daos.cache.UserDaoCache;
import com.personal.projectcalendar.types.User;
import com.personal.projectcalendar.types.requests.AddUserRequest;
import com.personal.projectcalendar.types.responses.AddUserResponse;

import javax.inject.Inject;

public class AddUserActivity {
    private UserDao userDao;
    private UserDaoCache userDaoCache;

    @Inject
    public AddUserActivity(UserDao userDao, UserDaoCache userDaoCache) {
        this.userDao        = userDao;
        this.userDaoCache   = userDaoCache;
    }

    public AddUserResponse execute(AddUserRequest request) {

        User newUser = UserModelConverter
                .convertToUser(request.getUserModel());

        userDao.addUser(newUser);
        userDaoCache.addUser(newUser);

        return AddUserResponse.builder()
                .withUserId(newUser.getUserId())
                .build();
    }
}
