package com.personal.projectcalendar.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.personal.projectcalendar.converters.dynamodb.ZonedDateTimeConverter;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@DynamoDBTable(tableName = "Calendar_events")
public class Event {
    @DynamoDBHashKey(attributeName = "event_id")
    private String eventId;
    @DynamoDBIndexHashKey(attributeName = "event_userId")
    private String userId;
    @DynamoDBIndexRangeKey(attributeName = "event_startDateTime")
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime srtDateTime;
    @DynamoDBAttribute(attributeName = "event_endDateTime")
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime endDateTime;
    @DynamoDBAttribute(attributeName = "event_title")
    private String eventTitle;
    @DynamoDBAttribute(attributeName = "event_body")
    private String eventBody;

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
}
