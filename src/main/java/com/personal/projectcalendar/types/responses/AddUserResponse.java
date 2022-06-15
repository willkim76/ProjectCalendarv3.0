package com.personal.projectcalendar.types.responses;

public class AddUserResponse {
    String userId;

    private AddUserResponse(AddUserResponse.Builder builder) {
        this.userId = builder.userId;
    }

    public String getUserId() {
        return userId;
    }

    public static AddUserResponse.Builder builder() {
        return new AddUserResponse.Builder();
    }

    public static class Builder {
        private String userId;

        private Builder() {}

        public Builder withUserId(String theUserId) {
            this.userId = theUserId;
            return this;
        }

        public AddUserResponse build() {
            return new AddUserResponse(this);
        }
    }
}
