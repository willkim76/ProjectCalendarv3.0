package com.personal.projectcalendar.models.dtos;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Defines the
 */
public class EventDto {
    @NotEmpty
    private String startDateTime;
    @NotEmpty
    private String endDateTime;

    private String eventTitle;
    private String eventBody;

    public EventDto() {}

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventDto)) {
            return false;
        }
        EventDto that = (EventDto) o;
        return Objects.equals(startDateTime, that.startDateTime) &&
                Objects.equals(endDateTime, that.endDateTime) &&
                Objects.equals(eventTitle, that.eventTitle) &&
                Objects.equals(eventBody, that.eventBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                startDateTime,
                endDateTime,
                eventTitle,
                eventBody);
    }
}
