package com.personal.projectcalendar.activities;

import com.personal.projectcalendar.daos.cache.UserDaoCache;
import com.personal.projectcalendar.security.hashable.Hashable;
import com.personal.projectcalendar.types.User;
import com.personal.projectcalendar.types.models.UserModel;
import com.personal.projectcalendar.types.requests.UserLoginRequest;
import com.personal.projectcalendar.types.responses.UserLoginResponse;

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
        UserModel userToVerify = request.getUserModel();
        Optional<User> user = userDaoCache.getUser(userToVerify.getUsername());

        boolean verified = false;
        if (user.isPresent()) {
            String hashToVerify = hashable.generateHash(userToVerify.getPassword(), user.get().getSalt());
            verified = user.get().getHash().equals(hashToVerify);
        }

        return UserLoginResponse.builder()
                .withMessage(user.isPresent() ? verified ? user.get().getUserid() : "PASSWORD INC" : "USERNAME DNE")
                .build();
    }
}
