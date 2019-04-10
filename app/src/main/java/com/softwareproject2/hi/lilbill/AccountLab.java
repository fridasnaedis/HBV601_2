package com.softwareproject2.hi.lilbill;

import android.content.Context;
import android.os.StrictMode;

import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.features.account.User;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountLab {


    private static AccountLab sAccountLab;
    //bara fyrir dummy lista, laga í get transaction aðferð
    private User mUser;
    private List<String> mAccountIds;
    private List<Transaction> mTransactions;
    private List<Account> mAccounts;


    public static AccountLab get(Context context) {
        if (sAccountLab == null) {
            sAccountLab = new AccountLab(context);
        }
        return sAccountLab;
    }

    private AccountLab(Context context) {

        Get get = new Get(context);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        String url1 = "https://lilbill.herokuapp.com/user/";


        //Fá EXTRA frá login activity með username og parsa við url1
        //Erum núna að getta eftir userID
        String EXTRA = "2";

        //Kalla á GET með user Id
        try {
            User user = get.getUserData(url1 + EXTRA);
            String username = user.getUsername();
            //Log.i(TAG, "" + username);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Sækja lista yfir öll account ID fyrir þennann user

        //mAccountIds =

        //nota for lykkju og kalla á getAccountData fyrir hvert ID

        //mAccounts =




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
            mUser = new User("Palli", "páll", "sson", "palli@palli.is");
            User user2 = new User("Jonni", "jón", "son", "jón@jón.is");

            Account account = new Account();
            account.setUser1("Palli");
            account.setUser2("Jonni");

            for (Transaction transaction : mTransactions) {
                transaction.setAccountId(account.getId());
            }
            account.setTransactionsList(mTransactions);
            balance = (float) Math.random()*100;
            account.setNetBalance(balance);
            mAccounts.add(account);
    }


    public List<Account> getAccounts(){
        return mAccounts;
    }


    //Ná í transaction eftir ID
    public Transaction getTransaction(UUID id) {
        for (Transaction transaction : mTransactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return  null;
    }

    // Ná í account eftir ID
    public Account getAccount(UUID id) {
        for (Account account : mAccounts) {
            if (account.getId().equals(id)) {
                //þegar að við náum í account og erum að skoða þeirra transactions þá viljum við +
                //uppfæra labbið
                mTransactions = account.getTransactionsList();
                return account;
            }
        }
        return null;
    }

}

