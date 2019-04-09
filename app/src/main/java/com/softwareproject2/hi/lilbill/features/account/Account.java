package com.softwareproject2.hi.lilbill.features.account;

import com.softwareproject2.hi.lilbill.features.transaction.Transaction;

import java.util.List;
import java.util.UUID;

public class Account {

    private UUID mId;
    private List<Transaction> mTransactionsList;
    private String mUser1;
    private String mUser2;
    private Float  mNetBalance;


    public Account() {
        mId = UUID.randomUUID();
        mNetBalance = new Float(0);
    }

    public UUID getId() {
        return mId;
    }

    public List getTransactionsList() {
        return mTransactionsList;
    }

    public void setTransactionsList(List<Transaction> transactionsList) {
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

    @Override
    public String toString() {
        return "Account{" +
                "mId=" + mId +
                ", mTransactionsList=" + mTransactionsList +
                ", mUser1='" + mUser1 + '\'' +
                ", mUser2='" + mUser2 + '\'' +
                ", mNetBalance=" + mNetBalance +
                '}';
    }
}
