package com.softwareproject2.hi.lilbill.features.account;

import java.util.List;
import java.util.UUID;

public class User {

    private UUID mId;
    private String mUsername;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private List mFriends;

    public User() {
    }

    public User(String username, String firstName, String lastName, String email) {
        mUsername = username;
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
    }

    public UUID getId() {
        return mId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public List getFriends() {
        return mFriends;
    }

    public void setFriends(List friends) {
        mFriends = friends;
    }
}
