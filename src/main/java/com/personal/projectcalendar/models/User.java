package com.personal.projectcalendar.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@DynamoDBTable(tableName = "Calendar_users")
public class User {
    @DynamoDBHashKey(attributeName = "user_id")
    private String userId;
    @DynamoDBAttribute(attributeName = "user_name")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "user_name-index")
    private String username;
    @DynamoDBAttribute(attributeName = "user_hash")
    private String hash;
    @DynamoDBAttribute(attributeName = "user_salt")
    private String salt;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", hash='" + hash + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId.equals(user.userId) &&
                username.equals(user.username) &&
                hash.equals(user.hash) &&
                salt.equals(user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, hash, salt);
    }
}
