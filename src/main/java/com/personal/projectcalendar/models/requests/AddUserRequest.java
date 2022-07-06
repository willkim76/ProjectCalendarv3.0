package com.personal.projectcalendar.models.requests;

import com.personal.projectcalendar.models.dtos.UserDto;

public class AddUserRequest {
    private UserDto userDto;

    private AddUserRequest(AddUserRequest.Builder builder) {
        this.userDto = builder.userDTO;
    }

    public UserDto getUserModel() {
        return userDto;
    }

    public static AddUserRequest.Builder builder() {
        return new AddUserRequest.Builder();
    }

    public static class Builder {
        private UserDto userDTO;

        private Builder() {}

        public Builder withUserModel(UserDto theUserDto) {
            this.userDTO = theUserDto;
            return this;
        }

        public AddUserRequest build() {
            return new AddUserRequest(this);
        }
    }
}
