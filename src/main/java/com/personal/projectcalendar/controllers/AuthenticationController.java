package com.personal.projectcalendar.controllers;

import com.personal.projectcalendar.ProjectCalendarApplication;
import com.personal.projectcalendar.activities.UserLoginActivity;
import com.personal.projectcalendar.dependencies.ServiceComponents;
import com.personal.projectcalendar.types.models.UserModel;
import com.personal.projectcalendar.types.requests.UserLoginRequest;
import com.personal.projectcalendar.types.responses.UserLoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class AuthenticationController {
    private static final ServiceComponents components = ProjectCalendarApplication.components;
    private static final int COOKIE_EXPIRATION_HOURS = 5;

    @PostMapping(value = "auth/login")
    public ResponseEntity<?> userLogin(
            @CookieValue(value = "userId", defaultValue = "STOP") String userId,
            @Valid @RequestBody UserModel userModel,
            HttpServletResponse httpResponse) {

        UserLoginActivity activity = components.provideUserLoginActivity();

        UserLoginRequest request = UserLoginRequest.builder()
                .withUserModel(userModel)
                .build();

        UserLoginResponse response = activity.execute(request);

        if (response.getMessage().equals("USERNAME DNE")) {
            String errorMessage = "Username does not exist!";
            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        }
        if (response.getMessage().equals("PASSWORD INC")) {
            String errorMessage = "Password is incorrect!";
            return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
        }

        Cookie cookie = new Cookie("userId", response.getMessage());
        cookie.setPath("/");
        cookie.setMaxAge(COOKIE_EXPIRATION_HOURS * 60 * 60);
        httpResponse.addCookie(cookie);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "logout")
    public ResponseEntity<?> userLogout(
            @CookieValue(value = "userId", defaultValue = "STOP") String userId,
            HttpServletResponse httpResponse) {

        if (userId.equals("STOP")) {
            String errorMessage = "User not logged in!";
            return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
        }

        Cookie cookie = new Cookie("userId", userId);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        httpResponse.addCookie(cookie);

        return new ResponseEntity<>("User logged out.", HttpStatus.OK);
    }
}
