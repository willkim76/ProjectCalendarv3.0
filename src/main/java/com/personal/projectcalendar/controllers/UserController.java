package com.personal.projectcalendar.controllers;

import com.personal.projectcalendar.ProjectCalendarApplication;
import com.personal.projectcalendar.activities.AddUserActivity;
import com.personal.projectcalendar.activities.UserLoginActivity;
import com.personal.projectcalendar.dependencies.ServiceComponents;
import com.personal.projectcalendar.types.models.UserModel;
import com.personal.projectcalendar.types.requests.AddUserRequest;
import com.personal.projectcalendar.types.requests.UserLoginRequest;
import com.personal.projectcalendar.types.responses.AddUserResponse;
import com.personal.projectcalendar.types.responses.UserLoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class UserController {
    private static final ServiceComponents components = ProjectCalendarApplication.components;
    private static final int COOKIE_EXPIRATION_HOURS = 5;

    @PostMapping(value = "users")
    public ResponseEntity<?> createUser(
            @CookieValue(value = "userId", defaultValue = "STOP") String userId,
            @Valid @RequestBody UserModel userModel,
            HttpServletResponse httpResponse) {

        AddUserActivity activity = components.provideAddUserActivity();

        AddUserRequest request = AddUserRequest.builder()
                .withUserModel(userModel)
                .build();

        AddUserResponse response = activity.execute(request);

        if (!userId.equals("STOP")) {
            String errorMessage = "Log out to create new user!";
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        }
        if (response.getUserId() == null) {
            String errorMessage = "Username already exists!";
            return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
        }

        Cookie cookie = new Cookie("userId", response.getUserId());
        httpResponse.addCookie(cookie);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
