package com.personal.projectcalendar.dependencies;

import com.personal.projectcalendar.activities.*;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        CacheModule.class,
        DaoModule.class,
        DynamoDBModule.class,
        SecurityModule.class
})
public interface ServiceComponents {

    AddEventActivity provideAddEventActivity();

    AddUserActivity provideAddUserActivity();

    EditEventActivity provideEditEventActivity();

    EditUserActivity provideEditUserActivity();

    DeleteEventActivity provideDeleteEventActivity();

    DeleteUserActivity provideDeleteUserActivity();

    GetEventsActivity provideGetEventActivity();

    UserLoginActivity provideUserLoginActivity();

}
