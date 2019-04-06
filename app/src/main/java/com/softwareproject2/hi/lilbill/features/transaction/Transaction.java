package com.softwareproject2.hi.lilbill.features.transaction;

import java.util.Date;
import java.util.UUID;

public class Transaction {

    private UUID mId;
    private Long accountId;
    private Long splitId;
    private Float amount;
    private String mDescription;
    private Date mDate;

    public Transaction() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getSplitId() {
        return splitId;
    }

    public void setSplitId(Long splitId) {
        this.splitId = splitId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public UUID getId() {
        return mId;
    }
}
