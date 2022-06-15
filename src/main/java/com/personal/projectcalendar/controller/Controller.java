package com.personal.projectcalendar.controller;

import com.personal.projectcalendar.ProjectCalendarApplication;
import com.personal.projectcalendar.activities.AddEventActivity;
import com.personal.projectcalendar.activities.AddUserActivity;
import com.personal.projectcalendar.activities.GetEventsActivity;
import com.personal.projectcalendar.activities.UserLoginActivity;
import com.personal.projectcalendar.dependencies.ServiceComponents;
import com.personal.projectcalendar.types.models.EventModel;
import com.personal.projectcalendar.types.models.UserModel;
import com.personal.projectcalendar.types.requests.AddEventRequest;
import com.personal.projectcalendar.types.requests.AddUserRequest;
import com.personal.projectcalendar.types.requests.GetEventsRequest;
import com.personal.projectcalendar.types.requests.UserLoginRequest;
import com.personal.projectcalendar.types.responses.AddUserResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class Controller {
    private static final ServiceComponents components = ProjectCalendarApplication.components;

    @GetMapping(value = "events/{user_id}")
    public ResponseEntity<?> getEvents(
            @CookieValue (value = "userId", defaultValue = "STOP") String userId,
            @PathVariable String user_id,
            @RequestParam String command) {
        GetEventsActivity activity = components.provideGetEventActivity();

        GetEventsRequest request = null;

        return new ResponseEntity<>(activity.execute(request), HttpStatus.OK);
    }

    @PostMapping(value = "events")
    public ResponseEntity<?> addEvent(
            @CookieValue (value = "userId", defaultValue = "STOP") String userId,
            @Valid @RequestBody EventModel eventModel) {
        AddEventActivity activity = components.provideAddEventActivity();

        AddEventRequest request = AddEventRequest.builder()
                .withEventModel(eventModel)
                .build();

        return new ResponseEntity<>(activity.execute(request), HttpStatus.OK);
    }

    @PostMapping(value = "users")
    public ResponseEntity<?> addUser(
            @Valid @RequestBody UserModel userModel,
            HttpServletResponse httpResponse) {
        AddUserActivity activity = components.provideAddUserActivity();

        AddUserRequest request = AddUserRequest.builder()
                .withUserModel(userModel)
                .build();

        AddUserResponse response = activity.execute(request);

        Cookie cookie = new Cookie("userId", response.getUserId());
        httpResponse.addCookie(cookie);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "users/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserModel userModel,
            HttpServletResponse response) {
        UserLoginActivity activity = components.provideUserLoginActivity();

        UserLoginRequest request = UserLoginRequest.builder()
                .withUserModel(userModel)
                .build();

        activity.execute(request);

        // validate login

        // Assign cookie

        Cookie cookie = new Cookie("userId", "Actual_value");
        cookie.setMaxAge(5 * 60 * 60);

        response.addCookie(cookie);

        return new ResponseEntity<>("ResponseEntity.accepted()", HttpStatus.OK);
    }

}
