package com.personal.projectcalendar.dependencies;

import com.personal.projectcalendar.daos.UserDao;
import com.personal.projectcalendar.daos.cache.UserDaoCache;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class CacheModule {

    @Provides
    @Singleton
    public UserDaoCache provideUserDaoCache(UserDao userDao) {
        return new UserDaoCache(userDao);
    }
}
