package com.softwareproject2.hi.lilbill.features.account;

import java.util.List;
import java.util.UUID;

public class Account {

    private UUID mId;
    private List mTransactionsList;
    private String mUser1;
    private String mUser2;
    private Float  mNetBalance;

    public Account(UUID id) {
        mId = id;
        mNetBalance = new Float(0);
    }

    public UUID getId() {
        return mId;
    }

    public List getTransactionsList() {
        return mTransactionsList;
    }

    public void setTransactionsList(List transactionsList) {
        mTransactionsList = transactionsList;
    }

    public String getUser1() {
        return mUser1;
    }

    public void setUser1(String user1) {
        mUser1 = user1;
    }

    public String getUser2() {
        return mUser2;
    }

    public void setUser2(String user2) {
        mUser2 = user2;
    }

    public Float getNetBalance() {
        return mNetBalance;
    }

    public void setNetBalance(Float netBalance) {
        mNetBalance = netBalance;
    }
}
