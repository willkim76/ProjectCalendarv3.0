package com.personal.projectcalendar.converters.models;

import com.personal.projectcalendar.types.Event;
import com.personal.projectcalendar.types.models.EventModel;
import com.personal.projectcalendar.utilities.EventUtilities;

public class EventModelConverter {

    public static Event convert(EventModel eventModel) {
        // validate start before end
        // validate start does not overlap with another event
        // validate end does not overlap with another event


//        return Event.builder()
//                .withEventId(EventUtilities.generateEventId())
//                .withUserId(eventModel.getUserId())
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
