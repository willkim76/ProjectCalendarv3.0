package com.personal.projectcalendar.activities;

import com.personal.projectcalendar.converters.models.EventModelConverter;
import com.personal.projectcalendar.daos.EventDao;
import com.personal.projectcalendar.daos.UserDao;
import com.personal.projectcalendar.types.Event;
import com.personal.projectcalendar.types.models.EventModel;
import com.personal.projectcalendar.types.models.UserModel;
import com.personal.projectcalendar.types.requests.AddEventRequest;
import com.personal.projectcalendar.types.responses.AddEventResponse;

import javax.inject.Inject;

public class AddEventActivity {
    private final EventDao eventDao;
    private final UserDao userDao;

    @Inject
    public AddEventActivity(EventDao eventDao, UserDao userDao) {
        this.eventDao   = eventDao;
        this.userDao    = userDao;
    }

    public AddEventResponse execute(final AddEventRequest request) {

        Event newEvent = EventModelConverter.convert(request.getEventModel());


        return null;
    }
}
