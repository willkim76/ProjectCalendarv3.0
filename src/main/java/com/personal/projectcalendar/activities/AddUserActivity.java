package com.personal.projectcalendar.activities;

import com.personal.projectcalendar.daos.UserDao;
import com.personal.projectcalendar.daos.cache.UserDaoCache;
import com.personal.projectcalendar.security.hashable.Hashable;
import com.personal.projectcalendar.types.User;
import com.personal.projectcalendar.types.models.UserModel;
import com.personal.projectcalendar.types.requests.AddUserRequest;
import com.personal.projectcalendar.types.responses.AddUserResponse;

import javax.inject.Inject;

import static com.personal.projectcalendar.utilities.UserUtilities.generateUserId;

public class AddUserActivity {
    private final Hashable hashable;
    private final UserDao userDao;
    private final UserDaoCache userDaoCache;

    @Inject
    public AddUserActivity(Hashable hashable,
                           UserDao userDao,
                           UserDaoCache userDaoCache) {
        this.hashable       = hashable;
        this.userDao        = userDao;
        this.userDaoCache   = userDaoCache;
    }

    public AddUserResponse execute(AddUserRequest request) {
        String newUsername      = request.getUserModel().getUsername();
        String newPassword      = request.getUserModel().getPassword();
        String userId           = null;
        UserModel newUserModel  = null;

        if (userDaoCache.getUser(newUsername).isEmpty()) {
            String userSalt = hashable.generateSalt();
            String userHash = hashable.generateHash(newPassword, userSalt);

            User newUser = User.builder()
                    .withUserId(generateUserId())
                    .withUsername(newUsername)
                    .withHash(userHash)
                    .withSalt(userSalt)
                    .build();

            userId = newUser.getUserId();

            userDao.addUser(newUser);
            userDaoCache.cacheUser(newUser);

            newUserModel = UserModel.builder()
                    .withUsername(newUsername)
                    .withPassword(newPassword)
                    .build();
        }

        return AddUserResponse.builder()
                .withUserid(userId)
                .withUserModel(newUserModel)
                .build();
    }
}
