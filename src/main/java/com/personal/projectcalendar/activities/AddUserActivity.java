package com.personal.projectcalendar.activities;

import com.personal.projectcalendar.converters.models.UserModelConverter;
import com.personal.projectcalendar.daos.UserDao;
import com.personal.projectcalendar.daos.cache.UserDaoCache;
import com.personal.projectcalendar.security.hashable.Hashable;
import com.personal.projectcalendar.types.User;
import com.personal.projectcalendar.types.requests.AddUserRequest;
import com.personal.projectcalendar.types.responses.AddUserResponse;

import javax.inject.Inject;

public class AddUserActivity {
    private Hashable hashable;
    private UserDao userDao;
    private UserDaoCache userDaoCache;

    @Inject
    public AddUserActivity(Hashable hashable, UserDao userDao, UserDaoCache userDaoCache) {
        this.hashable       = hashable;
        this.userDao        = userDao;
        this.userDaoCache   = userDaoCache;
    }

    public AddUserResponse execute(AddUserRequest request) {
        // Need to validate cleanse fields for length and characters
        String newUsername  = request.getUserModel().getUsername();
        String newPassword  = request.getUserModel().getPassword();

        String theSalt = hashable.generateSalt();
        String theHash = hashable.generateHash(newPassword, theSalt);

        User newUser = UserModelConverter.convertToUser(
                request.getUserModel(),
                theHash,
                theSalt);

        userDao.addUser(newUser);
        userDaoCache.cacheUser(newUser);

        return AddUserResponse.builder()
                .withUserId(newUser.getUserid())
                .build();
    }
}
