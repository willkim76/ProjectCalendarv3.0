package com.personal.projectcalendar.controllers;

import com.personal.projectcalendar.ProjectCalendarApplication;
import com.personal.projectcalendar.activities.AddEventActivity;
import com.personal.projectcalendar.activities.GetEventsActivity;
import com.personal.projectcalendar.dependencies.ServiceComponents;
import com.personal.projectcalendar.types.models.EventModel;
import com.personal.projectcalendar.types.requests.AddEventRequest;
import com.personal.projectcalendar.types.requests.GetEventsRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class EventController {
    private static final ServiceComponents components = ProjectCalendarApplication.components;

    @GetMapping(value = "events/{username}")
    public ResponseEntity<?> getEvents(
            @CookieValue (value = "userId", defaultValue = "STOP") String userId,
            @PathVariable String username,
            @RequestParam String command,
            HttpServletResponse response) {
        GetEventsActivity activity = components.provideGetEventActivity();

        GetEventsRequest request = null;

        return new ResponseEntity<>(activity.execute(request), HttpStatus.OK);
    }

    @PostMapping(value = "events")
    public ResponseEntity<?> addEvent(
            @CookieValue (value = "userId", defaultValue = "STOP") String userId,
            @Valid @RequestBody EventModel eventModel,
            HttpServletResponse response) {
        AddEventActivity activity = components.provideAddEventActivity();

        AddEventRequest request = AddEventRequest.builder()
                .withEventModel(eventModel)
                .withUserId("the userId from cookie")
                .build();

        return new ResponseEntity<>(activity.execute(request), HttpStatus.OK);
    }

}
