package com.personal.projectcalendar.daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.personal.projectcalendar.daos.cache.UserCacheKey;
import com.personal.projectcalendar.types.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDao {
    private final DynamoDBMapper dynamoDBMapper;

    public UserDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public User addUser(User user) {
        dynamoDBMapper.save(user);
        return user;
    }

    public Optional<User> getUser(UserCacheKey userCacheKey) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":user_name", new AttributeValue().withS(userCacheKey.getUserName()));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withIndexName("user_name-index")
                .withKeyConditionExpression("user_name = :user_name")
                .withExpressionAttributeValues(valueMap)
                .withConsistentRead(false);

        List<User> user = dynamoDBMapper.query(User.class, queryExpression);

        return Optional.ofNullable(user.isEmpty() ? null : user.get(0));
    }

    public void deleteUser(User user) {
        dynamoDBMapper.delete(user);
    }

    public boolean validateUser(String userName) {
        return this.getUser(new UserCacheKey(userName)) != null;
    }

}
