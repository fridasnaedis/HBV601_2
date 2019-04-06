package com.softwareproject2.hi.lilbill;

//populate-a alltaf þegar appið er opnað uppá nýtt við log-in


import android.content.Context;

import com.softwareproject2.hi.lilbill.features.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionLab {

    private static TransactionLab sTransactionLab;
    private List<Transaction> mTransactions;


    public static TransactionLab get(Context context) {
        if (sTransactionLab == null) {
            sTransactionLab = new TransactionLab(context);
        }
        return sTransactionLab;
    }

    private TransactionLab(Context context) {
        mTransactions = new ArrayList<>();
        Float value = new Float(2000);

        //Populate 100 random transaction
        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction();
            transaction.setAmount(value + i * 5);
            mTransactions.add(transaction);
        }
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
}
