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
                .userId(generateUserId())
                .username(userDTO.getUsername())
                .hash(theHash)
                .salt(theSalt)
                .build();
    }

    public static UserDto revertToUserModel(User user) {
        return UserDto.builder()
                .withUsername(user.getUsername())
                .withPassword(null)
                .build();
    }
}
