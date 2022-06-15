package com.personal.projectcalendar.daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.personal.projectcalendar.types.Event;

public class EventDao {
    private final DynamoDBMapper dynamoDBMapper;

    public EventDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public void addEvent(Event event) {

        dynamoDBMapper.save(event);
    }




}
