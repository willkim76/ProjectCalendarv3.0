package com.personal.projectcalendar.activities;

import com.personal.projectcalendar.daos.EventDao;
import com.personal.projectcalendar.daos.UserDao;
import com.personal.projectcalendar.security.encryptable.Encryptable;
import com.personal.projectcalendar.security.hashable.Hashable;
import com.personal.projectcalendar.models.dtos.EventDto;
import com.personal.projectcalendar.models.requests.AddEventRequest;
import com.personal.projectcalendar.models.responses.AddEventResponse;

import javax.inject.Inject;

public class AddEventActivity {
    private final Encryptable encryption;
    private final EventDao eventDao;
    private final Hashable hashable;
    private final UserDao userDao;

    @Inject
    public AddEventActivity(EventDao eventDao,
                            UserDao userDao,
                            Encryptable encryption,
                            Hashable hasher) {
        this.encryption = encryption;
        this.eventDao   = eventDao;
        this.hashable   = hasher;
        this.userDao    = userDao;
    }

    public AddEventResponse execute(final AddEventRequest request) {

        // validate the userId or if STOP cease terminate with error response

        // validate start before end
        // validate start does not overlap with another event
        // validate end does not overlap with another event

        EventDto eventDTO = request.getEventModel();


        return null;
    }
}
