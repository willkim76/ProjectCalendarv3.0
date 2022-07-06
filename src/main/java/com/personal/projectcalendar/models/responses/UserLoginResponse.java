package com.personal.projectcalendar.models.responses;

public class UserLoginResponse {
    private final String message;

    private UserLoginResponse(UserLoginResponse.Builder builder) {
        this.message = builder.message;
    }

    public String getMessage() {
        return message;
    }

    public static UserLoginResponse.Builder builder() {
        return new UserLoginResponse.Builder();
    }

    public static class Builder {
        private String message;

        private Builder() {}

        public Builder withMessage(String theUserId) {
            this.message = theUserId;
            return this;
        }

        public UserLoginResponse build() {
            return new UserLoginResponse(this);
        }
    }
}
