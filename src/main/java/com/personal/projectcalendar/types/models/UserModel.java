package com.personal.projectcalendar.types.models;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UserModel {
    @NotNull
    private String userName;
    @NotNull
    private String password;

    public UserModel() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return Objects.equals(userName, userModel.userName) &&
                Objects.equals(password, userModel.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                userName,
                password);
    }
}
