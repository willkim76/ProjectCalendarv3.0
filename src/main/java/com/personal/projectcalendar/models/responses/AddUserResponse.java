package com.personal.projectcalendar.models.responses;

import com.personal.projectcalendar.models.dtos.UserDto;

public class AddUserResponse {
    String userId;
    UserDto userDto;

    private AddUserResponse(AddUserResponse.Builder builder) {
        this.userId = builder.userId;
        this.userDto = builder.userDTO;
    }

    public String getUserId() {
        return userId;
    }

    public UserDto getUserModel() {
        return userDto;
    }

    public static AddUserResponse.Builder builder() {
        return new AddUserResponse.Builder();
    }

    public static class Builder {
        private String userId;
        private UserDto userDTO;

        private Builder() {}

        public Builder withUserid(String theUserId) {
            this.userId = theUserId;
            return this;
        }

        public Builder withUserModel(UserDto theUserDto) {
            this.userDTO = theUserDto;
            return this;
        }

        public AddUserResponse build() {
            return new AddUserResponse(this);
        }
    }
}
