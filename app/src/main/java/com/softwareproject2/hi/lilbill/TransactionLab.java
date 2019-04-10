package com.softwareproject2.hi.lilbill;

//populate-a alltaf þegar appið er opnað uppá nýtt við log-in

import android.content.Context;

import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.features.account.User;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionLab {

    private static TransactionLab sTransactionLab;
    private List<Transaction> mTransactions;
    private List<Account> mAccounts;


    public static TransactionLab get(Context context) {
        if (sTransactionLab == null) {
            sTransactionLab = new TransactionLab(context);
        }
        return sTransactionLab;
    }

    private TransactionLab(Context context) {

        mTransactions = new ArrayList<>();
        Float value = new Float(2000);

        //Populate 10 random transaction
        for (int i = 0; i < 5; i++) {
            Transaction transaction = new Transaction();
            transaction.setAmount(value + i * 5);
            mTransactions.add(transaction);
        }


        //Búum til lista til þess að halda utanum alla accounts
        mAccounts = new ArrayList<>();

        Float balance;
        //Populate-um með dummy gögnum

        for (int i = 0; i < 10; i++) {
            User user1 = new User("Palli", "páll", "sson", "palli@palli.is");
            User user2 = new User("Jonni", "jón", "son", "jón@jón.is");

            Account account = new Account();
            account.setUser1("user1");
            account.setUser2("user2");
            account.setTransactionsList(mTransactions);
            balance = (float) Math.random()*100;
            account.setNetBalance(balance);
            account.setTransactionsList(mTransactions);
            mAccounts.add(account);
        }

    }

    public List<Account> getAccounts(){
        return mAccounts;
    }

    public List<Transaction> getTransactions() {
        return mTransactions;
    }

    public Transaction getTransaction(UUID id) {
        for (Transaction transaction : mTransactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return  null;
    }

    public void addTransaction(Transaction t){
        mTransactions.add(t);
    }
}
