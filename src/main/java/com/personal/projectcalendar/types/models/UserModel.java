package com.personal.projectcalendar.types.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UserModel {
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    private String password;

    public UserModel() {}

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
        if (!(o instanceof UserModel)) {
            return false;
        }
        UserModel userModel = (UserModel) o;
        return Objects.equals(username, userModel.username) &&
                Objects.equals(password, userModel.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                username,
                password);
    }
}
