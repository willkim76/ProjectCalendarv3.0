package com.personal.projectcalendar.dependencies;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.personal.projectcalendar.daos.EventDao;
import com.personal.projectcalendar.daos.UserDao;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DaoModule {

    @Provides
    @Singleton
    public EventDao provideEventDao(DynamoDBMapper dynamoDBMapper) {
        return new EventDao(dynamoDBMapper);
    }


    @Provides
    @Singleton
    public UserDao provideUserDao(DynamoDBMapper dynamoDBMapper) {
        return new UserDao(dynamoDBMapper);
    }

}
