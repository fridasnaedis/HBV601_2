package com.softwareproject2.hi.lilbill.features.transaction;


/**
 * Transaction entity
 */
public class Transaction {

    private String mId;
    private String descr;
    private Float amount;
    private String mDate;
    private String accountId;

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


    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return descr;
    }

    public void setDescription(String description) {
        this.descr = description;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
