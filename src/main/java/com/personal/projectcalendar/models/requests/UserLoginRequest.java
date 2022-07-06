package com.personal.projectcalendar.models.requests;

import com.personal.projectcalendar.models.dtos.UserDto;

public class UserLoginRequest {
    private UserDto userDto;

    private UserLoginRequest(UserLoginRequest.Builder builder) {
        this.userDto = builder.userDTO;
    }

    public UserDto getUserModel() {
        return userDto;
    }

    public static UserLoginRequest.Builder builder() {
        return new UserLoginRequest.Builder();
    }

    public static class Builder {
        private UserDto userDTO;

        private Builder() {}

        public UserLoginRequest.Builder withUserModel(UserDto theUserDto) {
            this.userDTO = theUserDto;
            return this;
        }

        public UserLoginRequest build() {
            return new UserLoginRequest(this);
        }
    }
}
