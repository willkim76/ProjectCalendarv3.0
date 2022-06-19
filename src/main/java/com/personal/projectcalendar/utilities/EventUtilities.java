package com.personal.projectcalendar.utilities;

import java.util.UUID;

/**
 *
 */
public class EventUtilities {

    public static String generateEventId() {
        return String.format("event:%s", UUID.randomUUID());
    }

}
