package com.personal.projectcalendar.converters.models;

import com.personal.projectcalendar.types.User;
import com.personal.projectcalendar.types.models.UserModel;
import com.personal.projectcalendar.utilities.UserUtilities;

public class UserModelConverter {

    private UserModelConverter() {}

    public static User convertToUser(UserModel userModel) {
        String salt = UserUtilities.generateSalt();

        return User.builder()
                .withUserId(UserUtilities.generateUserId())
                .withUserName(userModel.getUserName())
                .withHash(
                        UserUtilities.generatePBKDF2Hash(userModel.getPassword(), salt)
                )
                .withSalt(salt)
                .build();
    }

    public static UserModel revertToUserModel(User user) {
        throw new UnsupportedOperationException();
    }
}
