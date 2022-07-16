package com.personal.projectcalendar.activities;

import com.personal.projectcalendar.daos.UserDao;
import com.personal.projectcalendar.daos.cache.UserDaoCache;
import com.personal.projectcalendar.security.hashable.Hashable;
import com.personal.projectcalendar.models.User;
import com.personal.projectcalendar.models.dtos.UserDto;
import com.personal.projectcalendar.models.requests.AddUserRequest;
import com.personal.projectcalendar.models.responses.AddUserResponse;

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
        UserDto newUserDto = null;

        if (userDaoCache.getUser(newUsername).isEmpty()) {
            String userSalt = hashable.generateSalt();
            String userHash = hashable.generateHash(newPassword, userSalt);

            User newUser = User.builder()
                    .userId(generateUserId())
                    .username(newUsername)
                    .hash(userHash)
                    .salt(userSalt)
                    .build();

            userId = newUser.getUserId();

            userDao.addUser(newUser);
            userDaoCache.cacheUser(newUser);

            newUserDto = UserDto.builder()
                    .withUsername(newUsername)
                    .withPassword(newPassword)
                    .build();
        }

        return AddUserResponse.builder()
                .withUserid(userId)
                .withUserModel(newUserDto)
                .build();
    }
}
