package com.personal.projectcalendar.types;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

@DynamoDBTable(tableName = "Calendar_users")
public class User {
    private String userid;
    private String username;
    private String hash;
    private String salt;

    /**
     * No argument constructor for DynamoDB.
     */
    public User() {}

    private User(User.Builder builder) {
        this.userid     = builder.userid;
        this.username   = builder.username;
        this.hash       = builder.hash;
        this.salt       = builder.salt;
    }

    @DynamoDBHashKey(attributeName = "user_id")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @DynamoDBAttribute(attributeName = "user_name")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "user_name-index")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        return userid.equals(user.userid) &&
                username.equals(user.username) &&
                hash.equals(user.hash) &&
                salt.equals(user.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                userid,
                username,
                hash,
                salt);
    }

    public static User.Builder builder() {
        return new User.Builder();
    }

    public static class Builder {
        private String userid;
        private String username;
        private String hash;
        private String salt;

        private Builder() {}

        public Builder withUserid(String theUserid) {
            this.userid = theUserid;
            return this;
        }

        public Builder withUsername(String theUsername) {
            this.username = theUsername;
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
