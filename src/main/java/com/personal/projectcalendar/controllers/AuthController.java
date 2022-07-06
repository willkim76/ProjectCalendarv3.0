package com.personal.projectcalendar.controllers;

import com.personal.projectcalendar.ProjectCalendarApplication;
import com.personal.projectcalendar.activities.UserLoginActivity;
import com.personal.projectcalendar.dependencies.ServiceComponents;
import com.personal.projectcalendar.models.dtos.UserDto;
import com.personal.projectcalendar.models.requests.UserLoginRequest;
import com.personal.projectcalendar.models.responses.UserLoginResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class AuthController {
    private static final ServiceComponents components = ProjectCalendarApplication.components;
    private static final int COOKIE_EXPIRATION_HOURS    = 5;
    private static final String USERNAME_DNE_ERROR_MSG  = "Username does not exist!";
    private static final String PASSWORD_INC_ERROR_MSG  = "Password is incorrect!";

    @PostMapping(value = "auth/login")
    public ResponseEntity<?> userLogin(
            @CookieValue(value = "userId", defaultValue = "STOP") String userId,
            @Valid @RequestBody UserDto userDTO,
            HttpServletResponse httpResponse) {

        UserLoginActivity activity = components.provideUserLoginActivity();

        UserLoginRequest request = UserLoginRequest.builder()
                .withUserModel(userDTO)
                .build();

        UserLoginResponse response = activity.execute(request);

        if (response.getMessage().equals("USERNAME DNE")) {
            return new ResponseEntity<>(USERNAME_DNE_ERROR_MSG, HttpStatus.UNAUTHORIZED);
        }
        if (response.getMessage().equals("PASSWORD INC")) {
            return new ResponseEntity<>(PASSWORD_INC_ERROR_MSG, HttpStatus.UNAUTHORIZED);
        }

        httpResponse.addCookie(
                this.generateUserIdCookie(response.getMessage(), COOKIE_EXPIRATION_HOURS));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "logout")
    public ResponseEntity<?> userLogout(
            @CookieValue(value = "userId", defaultValue = "STOP") String userId,
            HttpServletResponse httpResponse) {

        if (userId.equals("STOP")) {
            String errorMessage = "User not logged in!";
            return new ResponseEntity<>(errorMessage, HttpStatus.OK);
        }

        httpResponse.addCookie(this.generateUserIdCookie(userId, 0));

        return new ResponseEntity<>("User logged out.", HttpStatus.OK);
    }

    private Cookie generateUserIdCookie(String userId, int maxTimeHrs) {
        Cookie cookie = new Cookie("userId", userId);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxTimeHrs * 60 * 60);

        return cookie;
    }
}
