package com.personal.projectcalendar.types.models;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class EventModel {
    @NotNull
    private String userId;
    @NotNull
    private String startDateTime;
    @NotNull
    private String endDateTime;

    private String eventTitle;
    private String eventBody;

    public EventModel() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
        if (!(o instanceof EventModel)) {
            return false;
        }
        EventModel that = (EventModel) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(startDateTime, that.startDateTime) &&
                Objects.equals(endDateTime, that.endDateTime) &&
                Objects.equals(eventTitle, that.eventTitle) &&
                Objects.equals(eventBody, that.eventBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                userId,
                startDateTime,
                endDateTime,
                eventTitle,
                eventBody);
    }
}
