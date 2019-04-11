package com.softwareproject2.hi.lilbill.features.transaction;

public class Transaction {

    private String mId;
    private String accountId;
    private String splitId;
    private String deskr;
    private Float amount;
    private String mDate;

    public Transaction() {

    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getSplitId() {
        return splitId;
    }

    public void setSplitId(String splitId) {
        this.splitId = splitId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return deskr;
    }

    public void setDescription(String description) {
        deskr = description;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
