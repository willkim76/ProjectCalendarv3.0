package com.personal.projectcalendar.types.requests;

import com.personal.projectcalendar.types.models.UserModel;

public class UserLoginRequest {
    private UserModel userModel;

    private UserLoginRequest(UserLoginRequest.Builder builder) {
        this.userModel = builder.userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public static UserLoginRequest.Builder builder() {
        return new UserLoginRequest.Builder();
    }

    public static class Builder {
        private UserModel userModel;

        private Builder() {}

        public UserLoginRequest.Builder withUserModel(UserModel theUserModel) {
            this.userModel = theUserModel;
            return this;
        }

        public UserLoginRequest build() {
            return new UserLoginRequest(this);
        }
    }
}
