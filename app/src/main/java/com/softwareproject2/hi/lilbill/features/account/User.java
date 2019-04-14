package com.softwareproject2.hi.lilbill.features.account;

import java.util.List;


/**
 * User entity
 */
public class User {

    private String mId;
    private String mUsername;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private List<String> mFriends;

    public User() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
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

    public void setFriends(List<String> friends) {
        mFriends = friends;
    }

    public void addFriend(String friend) {mFriends.add(friend);}

    @Override
    public String toString() {
        return "User{" +
                "mId=" + mId +
                ", mUsername='" + mUsername + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mFriends=" + mFriends +
                '}';
    }
}
