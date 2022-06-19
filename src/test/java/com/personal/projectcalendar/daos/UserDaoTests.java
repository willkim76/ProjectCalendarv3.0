package com.personal.projectcalendar.daos;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.personal.projectcalendar.daos.cache.UserCacheKey;
import com.personal.projectcalendar.dependencies.DaoModule;
import com.personal.projectcalendar.dependencies.DynamoDBModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserDaoTests {
    private UserDao userDao;

    @BeforeEach
    void setup() {
        this.userDao = new UserDao(new DynamoDBModule().dynamoDBMapperProvider());
    }

    @Test
    void test1() {
        userDao.getUser(new UserCacheKey("WillTheThrill"));
    }
}
