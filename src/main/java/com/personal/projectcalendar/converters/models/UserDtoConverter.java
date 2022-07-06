package com.personal.projectcalendar.converters.models;

import com.personal.projectcalendar.models.User;
import com.personal.projectcalendar.models.dtos.UserDto;

import static com.personal.projectcalendar.utilities.UserUtilities.generateUserId;

public class UserDtoConverter {

    private UserDtoConverter() {}

    public static User convertToUser(UserDto userDTO,
                                     String theHash,
                                     String theSalt) {
        return User.builder()
                .withUserId(generateUserId())
                .withUsername(userDTO.getUsername())
                .withHash(theHash)
                .withSalt(theSalt)
                .build();
    }

    public static UserDto revertToUserModel(User user) {
        return UserDto.builder()
                .withUsername(user.getUsername())
                .withPassword(null)
                .build();
    }
}
