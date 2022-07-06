package com.personal.projectcalendar.models.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class UserDto {
    @NotEmpty(message = "A username is required.")
    @Size(min = 2, max = 30, message = "The length of the username must be between 2 and 30 characters.")
    private String username;
    @NotEmpty(message = "A password is required.")
    @Size(min = 5, max = 30, message = "The length of the password must be between 5 and 30 characters.")
    private String password;

    public UserDto() {}

    public UserDto(UserDto.Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDto)) {
            return false;
        }
        UserDto userDTO = (UserDto) o;
        return Objects.equals(username, userDTO.username) &&
                Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                username,
                password);
    }

    public static UserDto.Builder builder() {
        return new UserDto.Builder();
    }

    public static class Builder {
        private String username;
        private String password;

        private Builder() {}

        public Builder withUsername(String theUsername) {
            this.username = theUsername;
            return this;
        }

        public Builder withPassword(String thePassword) {
            this.password = thePassword;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }
    }
}
