package com.personal.projectcalendar.converters.models;

import com.personal.projectcalendar.types.User;
import com.personal.projectcalendar.types.models.UserModel;

import static com.personal.projectcalendar.utilities.UserUtilities.generateUserId;

public class UserModelConverter {

    private UserModelConverter() {}

    public static User convertToUser(UserModel userModel,
                                     String theHash,
                                     String theSalt) {
        return User.builder()
                .withUserId(generateUserId())
                .withUsername(userModel.getUsername())
                .withHash(theHash)
                .withSalt(theSalt)
                .build();
    }

    public static UserModel revertToUserModel(User user) {
        return UserModel.builder()
                .withUsername(user.getUsername())
                .withPassword(null)
                .build();
    }
}
