package com.personal.projectcalendar.converters.models;

import com.personal.projectcalendar.types.Event;
import com.personal.projectcalendar.types.models.EventModel;
import com.personal.projectcalendar.utilities.EventUtilities;

public class EventModelConverter {

    public static Event convert(EventModel eventModel, String userid) {

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

    public static EventModel revert(Event event) {
        return null;
    }
}
