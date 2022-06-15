package com.personal.projectcalendar.daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.personal.projectcalendar.daos.cache.UserCacheKey;
import com.personal.projectcalendar.types.User;

public class UserDao {
    private final DynamoDBMapper dynamoDBMapper;

    public UserDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public User addUser(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public User getUser(UserCacheKey userCacheKey) {
        User userKey = User.builder()
                .withUserId(userCacheKey.getUserName())
                .build();

        return dynamoDBMapper.load(userKey);
    }

    public void deleteUser(User user) {
        dynamoDBMapper.delete(user);
    }

    public boolean validateUser(String userName) {
        return this.getUser(new UserCacheKey(userName)) != null;
    }

}
