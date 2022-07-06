package com.personal.projectcalendar.activities;

import com.personal.projectcalendar.daos.cache.UserDaoCache;
import com.personal.projectcalendar.security.hashable.Hashable;
import com.personal.projectcalendar.models.User;
import com.personal.projectcalendar.models.dtos.UserDto;
import com.personal.projectcalendar.models.requests.UserLoginRequest;
import com.personal.projectcalendar.models.responses.UserLoginResponse;

import javax.inject.Inject;
import java.util.Optional;

public class UserLoginActivity {
    private final UserDaoCache userDaoCache;
    private final Hashable hashable;

    @Inject
    public UserLoginActivity(UserDaoCache userDaoCache, Hashable hashable) {
        this.hashable = hashable;
        this.userDaoCache = userDaoCache;
    }

    public UserLoginResponse execute(UserLoginRequest request) {
        UserDto userToVerify = request.getUserModel();
        Optional<User> user = userDaoCache.getUser(userToVerify.getUsername());

        String message = "USERNAME DNE";

        if (user.isPresent()) {
            User existingUser = user.get();
            String hashToVerify = hashable.generateHash(userToVerify.getPassword(),
                    existingUser.getSalt());

            message = existingUser.getHash().equals(hashToVerify) ?
                    existingUser.getUserId() :
                    "PASSWORD INC";
        }

        return UserLoginResponse.builder()
                .withMessage(message)
                .build();
    }
}
