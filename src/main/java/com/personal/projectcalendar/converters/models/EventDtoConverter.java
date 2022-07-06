package com.personal.projectcalendar.converters.models;

import com.personal.projectcalendar.models.Event;
import com.personal.projectcalendar.models.dtos.EventDto;

public class EventDtoConverter {

    public static Event convert(EventDto eventDTO, String userId) {

//        return Event.builder()
//                .withEventId(EventUtilities.generateEventId())
//                .withUserId(userId)
//                .withSrtZonedDateTime()
//                .withEndZonedDateTime()
//                .withEventTitle()
//                .withEventBody()
//                .build();
        return null;
    }

    public static EventDto revert(Event event) {
        return null;
    }
}
