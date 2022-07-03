package com.personal.projectcalendar.types.responses;

import com.personal.projectcalendar.types.models.UserModel;

public class AddUserResponse {
    String userId;
    UserModel userModel;

    private AddUserResponse(AddUserResponse.Builder builder) {
        this.userId = builder.userId;
        this.userModel = builder.userModel;
    }

    public String getUserId() {
        return userId;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public static AddUserResponse.Builder builder() {
        return new AddUserResponse.Builder();
    }

    public static class Builder {
        private String userId;
        private UserModel userModel;

        private Builder() {}

        public Builder withUserid(String theUserId) {
            this.userId = theUserId;
            return this;
        }

        public Builder withUserModel(UserModel theUserModel) {
            this.userModel = theUserModel;
            return this;
        }

        public AddUserResponse build() {
            return new AddUserResponse(this);
        }
    }
}
