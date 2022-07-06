package com.personal.projectcalendar.controllers;

import com.personal.projectcalendar.ProjectCalendarApplication;
import com.personal.projectcalendar.activities.AddUserActivity;
import com.personal.projectcalendar.activities.EditUserActivity;
import com.personal.projectcalendar.dependencies.ServiceComponents;
import com.personal.projectcalendar.models.dtos.UserDto;
import com.personal.projectcalendar.models.requests.AddUserRequest;
import com.personal.projectcalendar.models.responses.AddUserResponse;

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
            @Valid @RequestBody UserDto userDTO,
            HttpServletResponse httpResponse) {

        AddUserActivity activity = components.provideAddUserActivity();

        AddUserRequest request = AddUserRequest.builder()
                .withUserModel(userDTO)
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

        httpResponse.addCookie(
                this.generateUserIdCookie(response.getUserId(), COOKIE_EXPIRATION_HOURS));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // TODO
    @PutMapping(value = "users")
    public ResponseEntity<?> updateUser(
            @CookieValue(value = "userId", defaultValue = "STOP") String userId,
            @Valid @RequestBody UserDto userDTO,
            HttpServletResponse response) {

        EditUserActivity activity = components.provideEditUserActivity();

        return null;
    }

    private Cookie generateUserIdCookie(String userId, int maxTimeHrs) {
        Cookie cookie = new Cookie("userId", userId);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxTimeHrs * 60 * 60);

        return cookie;
    }
}
