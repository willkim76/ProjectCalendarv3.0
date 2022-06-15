package com.personal.projectcalendar.types.requests;

import com.personal.projectcalendar.types.models.UserModel;

public class AddUserRequest {
    private UserModel userModel;

    private AddUserRequest(AddUserRequest.Builder builder) {
        this.userModel = builder.userModel;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public static AddUserRequest.Builder builder() {
        return new AddUserRequest.Builder();
    }

    public static class Builder {
        private UserModel userModel;

        private Builder() {}

        public Builder withUserModel(UserModel theUserModel) {
            this.userModel = theUserModel;
            return this;
        }

        public AddUserRequest build() {
            return new AddUserRequest(this);
        }
    }
}
