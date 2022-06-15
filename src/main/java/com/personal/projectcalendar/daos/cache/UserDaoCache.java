package com.personal.projectcalendar.daos.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.personal.projectcalendar.daos.UserDao;
import com.personal.projectcalendar.types.User;

import java.util.concurrent.TimeUnit;

public class UserDaoCache {
    private LoadingCache<UserCacheKey, User> userCache;

    public UserDaoCache(final UserDao delegateDao) {
        this.userCache = CacheBuilder.newBuilder()
                .maximumSize(10_000)
                .expireAfterAccess(5, TimeUnit.HOURS)
                .build(CacheLoader.from(delegateDao::getUser));
    }

    /**
     * Retrieves a User from the Cache or Database.
     * @param userName
     * @return
     */
    public User getUser(String userName) {
        return userCache.getUnchecked(new UserCacheKey(userName));
    }

    /**
     * Adds a User to the Cache.
     * @param user to add the to the cache
     */
    public void addUser(User user) {
        userCache.put(new UserCacheKey(user.getUserId()), user);
    }

    /**
     * Removes a User from the Cache.
     * @param userName String to use
     */
    public void deleteUser(String userName) {
        userCache.invalidate(new UserCacheKey(userName));
    }

}
