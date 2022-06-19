package com.personal.projectcalendar.daos.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.personal.projectcalendar.daos.UserDao;
import com.personal.projectcalendar.types.User;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class UserDaoCache {
    private LoadingCache<UserCacheKey, Optional<User>> userCache;

    public UserDaoCache(final UserDao delegateDao) {
        this.userCache = CacheBuilder.newBuilder()
                .maximumSize(10_000)
                .expireAfterAccess(1, TimeUnit.HOURS)
                .build(CacheLoader.from(delegateDao::getUser));
    }

    /**
     * Retrieves a User from the Cache or Database.
     * @param userName
     * @return
     */
    public Optional<User> getUser(String userName) {
        return userCache.getUnchecked(new UserCacheKey(userName));
    }

    /**
     * Adds a User to the Cache by username.
     * @param user to add the to the cache
     */
    public void cacheUser(User user) {
        userCache.put(new UserCacheKey(user.getUsername()), Optional.of(user));
    }

    /**
     * Removes a User from the Cache.
     * @param userName String to use
     */
    public void invalidateUser(String userName) {
        userCache.invalidate(new UserCacheKey(userName));
    }

}
