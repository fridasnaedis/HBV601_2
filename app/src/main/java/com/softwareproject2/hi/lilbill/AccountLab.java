package com.softwareproject2.hi.lilbill;

import android.content.Context;

import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.features.account.User;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountLab {


    private static AccountLab sAccountLab;
    //bara fyrir dummy lista, laga í get transaction aðferð
    private List<Transaction> mTransactions;
    private List<Account> mAccounts;


    public static AccountLab get(Context context) {
        if (sAccountLab == null) {
            sAccountLab = new AccountLab(context);
        }
        return sAccountLab;
    }

    private AccountLab(Context context) {

        //kalla á network manager og sækja account sem inniheldur lista af öllum transaction

        //mAccounts = networkManager.getBLA

        // Dummy stöff

        mTransactions = new ArrayList<>();
        Float value = new Float(2000);

        //Populate 100 random transaction
        for (int i = 0; i < 10; i++) {
            Transaction transaction = new Transaction();
            transaction.setAmount(value + i * 5);
            mTransactions.add(transaction);
        }


        // Búum til lista til þess að halda utanum alla accounts
        mAccounts = new ArrayList<>();

        Float balance;
        //Populate-um með dummy gögnum

        //for (int i = 0; i < 10; i++) {
            User user1 = new User("Palli", "páll", "sson", "palli@palli.is");
            User user2 = new User("Jonni", "jón", "son", "jón@jón.is");

            Account account = new Account();
            account.setUser1("user1");
            account.setUser2("user2");

            for (Transaction transaction : mTransactions) {
                transaction.setAccountId(account.getId());
            }
            account.setTransactionsList(mTransactions);
            balance = (float) Math.random()*100;
            account.setNetBalance(balance);
            mAccounts.add(account);
        //}
    }


    public List<Account> getAccounts(){
        return mAccounts;
    }


    /*ná í eitt transaction út frá transactionUUID og account UUID
    public Transaction getTransaction(UUID accountId, UUID transactionId){

        //Account account = getAccount(accountId);
        //List<Transaction> mTransactions = account.getTransactionsList();
        for(Transaction transaction : mTransactions){
            if (transaction.getId().equals(transactionId)){
                return transaction;
            }
        }
        return null;
    }*/


    //to do, laga það að sækja account id extra svo að hægt sé að nota fallið fyrir ofan
    public Transaction getTransaction(UUID id) {
        for (Transaction transaction : mTransactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return  null;
    }

    //ná í einn Account
    public Account getAccount(UUID id) {
        for (Account account : mAccounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

}

