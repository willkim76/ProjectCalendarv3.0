package com.personal.projectcalendar.types;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.personal.projectcalendar.utilities.UserUtilities;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "Calendar_users")
public class User {
    private String userId;
    private String userName;
    private String hash;
    private String salt;

    /**
     * No argument constructor for DynamoDB.
     */
    public User() {}

    private User(User.Builder builder) {
        this.userId     = builder.userId;
        this.userName   = builder.userName;
        this.hash       = builder.hash;
        this.salt       = builder.salt;
    }

    @DynamoDBHashKey(attributeName = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @DynamoDBAttribute(attributeName = "user_hash")
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @DynamoDBAttribute(attributeName = "user_salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return userId.equals(user.userId) &&
                userName.equals(user.userName) &&
                hash.equals(user.hash) &&
                salt.equals(user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                userId,
                userName,
                hash,
                salt);
    }

    public static User.Builder builder() {
        return new User.Builder();
    }

    public static class Builder {
        private String userId;
        private String userName;
        private String hash;
        private String salt;

        private Builder() {}

        public Builder withUserId(String theUserId) {
            this.userId = theUserId;
            return this;
        }

        public Builder withUserName(String theUserName) {
            this.userName = theUserName;
            return this;
        }

        public Builder withHash(String theHash) {
            this.hash = theHash;
            return this;
        }

        public Builder withSalt(String theSalt) {
            this.salt = theSalt;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
