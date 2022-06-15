package com.personal.projectcalendar.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.ZonedDateTime;

public class ZonedDateTimeConverter implements DynamoDBTypeConverter<String, ZonedDateTime> {

    @Override
    public String convert(ZonedDateTime object) {
        return object.toString();
    }

    @Override
    public ZonedDateTime unconvert(String object) {
        return ZonedDateTime.parse(object);
    }
}
