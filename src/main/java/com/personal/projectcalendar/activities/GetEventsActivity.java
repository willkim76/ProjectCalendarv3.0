package com.personal.projectcalendar.activities;

import com.personal.projectcalendar.daos.EventDao;
import com.personal.projectcalendar.daos.UserDao;
import com.personal.projectcalendar.models.requests.GetEventsRequest;
import com.personal.projectcalendar.models.responses.GetEventsResponse;

import javax.inject.Inject;

public class GetEventsActivity {
    private final EventDao eventDao;
    private final UserDao userDao;

    @Inject
    public GetEventsActivity(EventDao eventDao, UserDao userDao) {
        this.eventDao   = eventDao;
        this.userDao    = userDao;
    }

    public GetEventsResponse execute(GetEventsRequest request) {


        return null;
    }
}
