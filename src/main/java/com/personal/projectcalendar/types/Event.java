package com.personal.projectcalendar.types;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.personal.projectcalendar.converters.ZonedDateTimeConverter;

import java.time.ZonedDateTime;
import java.util.Objects;

@DynamoDBTable(tableName = "Calendar_events")
public class Event {
    private String eventId;
    private String userId;
    private ZonedDateTime srtDateTime;
    private ZonedDateTime endDateTime;
    private String eventTitle;
    private String eventBody;

    /**
     * No argument constructor for DynamoDB.
     */
    public Event() {}

    /**
     * Private Event.Builder argument constructor.
     * @param builder Event.Builder to use.
     */
    private Event(Event.Builder builder) {
        this.eventId        = builder.eventId;
        this.userId         = builder.userId;
        this.srtDateTime    = builder.srtDateTime;
        this.endDateTime    = builder.endDateTime;
        this.eventTitle     = builder.eventTitle;
        this.eventBody      = builder.eventBody;
    }

    @DynamoDBHashKey(attributeName = "event_id")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @DynamoDBIndexHashKey(attributeName = "event_userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBIndexRangeKey(attributeName = "event_startDateTime")
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    public ZonedDateTime getSrtDateTime() {
        return srtDateTime;
    }

    public void setSrtDateTime(ZonedDateTime srtDateTime) {
        this.srtDateTime = srtDateTime;
    }

    @DynamoDBAttribute(attributeName = "event_endDateTime")
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    @DynamoDBAttribute(attributeName = "event_title")
    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    @DynamoDBAttribute(attributeName = "event_body")
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
        if (!(o instanceof Event)) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(eventId, event.eventId) &&
                Objects.equals(userId, event.userId) &&
                Objects.equals(srtDateTime, event.srtDateTime) &&
                Objects.equals(endDateTime, event.endDateTime) &&
                Objects.equals(eventTitle, event.eventTitle) &&
                Objects.equals(eventBody, event.eventBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                eventId,
                userId,
                srtDateTime,
                endDateTime,
                eventTitle,
                eventBody);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", srtDateTime=" + srtDateTime +
                ", endDateTime=" + endDateTime +
                ", eventTitle='" + eventTitle + '\'' +
                ", eventBody='" + eventBody + '\'' +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String eventId;
        private String userId;
        private ZonedDateTime srtDateTime;
        private ZonedDateTime endDateTime;
        private String eventTitle;
        private String eventBody;

        private Builder() {}

        public Builder withEventId(String theEventId) {
            this.eventId = theEventId;
            return this;
        }

        public Builder withUserId(String theUserId) {
            this.userId = theUserId;
            return this;
        }

        public Builder withSrtZonedDateTime(ZonedDateTime theSrtZonedDateTime) {
            this.srtDateTime = theSrtZonedDateTime;
            return this;
        }

        public Builder withEndZonedDateTime(ZonedDateTime theEndZonedDateTime) {
            this.endDateTime = theEndZonedDateTime;
            return this;
        }

        public Builder withEventTitle(String theEventTitle) {
            this.eventTitle = theEventTitle;
            return this;
        }

        public Builder withEventBody(String theEventBody) {
            this.eventBody = theEventBody;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
