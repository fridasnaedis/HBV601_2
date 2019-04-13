package com.softwareproject2.hi.lilbill;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.features.account.User;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.softwareproject2.hi.lilbill.TransactionActivity.TAG;

public class AccountLab {


    private static AccountLab sAccountLab;
    //bara fyrir dummy lista, laga í get transaction aðferð
    private User mUser;
    private List<Transaction> mTransactions;
    private List<String> mAccountIds;
    private List<Account> mAccounts;
    private Get get;
    private Post post;


    public static AccountLab get(Context context) {
        if (sAccountLab == null) {
            sAccountLab = new AccountLab(context);
        }
        return sAccountLab;
    }

    private AccountLab(Context context) {

        get = new Get(context);
        post = new Post(context);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

    }


    public List<Account> getAccounts() {
        return mAccounts;
    }



    //Ná í transaction eftir ID
    public Transaction getTransaction(String id) {
        for (Transaction transaction : mTransactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return null;
    }


    // Ná í account eftir ID
    public Account getAccount(String id) {
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

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    // set
    // set
    public void createTransaction(Transaction t, String id) {

        final String response;
        final String transactionId;
        final String date;

        Account account = getAccount(id);
        account.setNetBalance(account.getNetBalance()+t.getAmount());
        try {
            response = post.postJsonFromTransaction(t, mUser.getId());
            Log.d(TAG, "line 62" + response);

            JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

            transactionId = jsonObject.get("id").getAsString();
            date = jsonObject.get("date").getAsString();
            t.setId(transactionId);
            t.setDate(date);

        } catch (IOException e) {
            e.printStackTrace();
        }


        account.addTransaction(t);
        mTransactions = account.getTransactionsList();



    }

    public String addFriend(String userId, String friendUserName){

        try {
            String response =  post.postJsonFromAddFriend(userId, friendUserName);
            String error1 = "no user with username:";
            String error2 = "user with username:";

            if(!response.contains(error1) && !response.contains(error2)) {

                JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
                Account a = new Account();
                final String mUser2 = jsonObject.get("user2").getAsString();
                final String mUser1 = jsonObject.get("user1").getAsString();
                final String mAccountId = jsonObject.get("id").getAsString();

                a.setId(mAccountId);

                a.setUser1(mUser1);
                a.setUser2(mUser2);

                mAccounts.add(a);
            }

            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }




        return null;
    }


    public void logIn(String username, String password) {
        //Gera post request á /login
        //setja mUser = user sem kemur úr response

        String login = "https://lilbill.herokuapp.com/login/";

        //Kalla á GET með user Id
        try {
            mUser = get.getUserData(login + username);
            //Log.i(TAG, "" + username);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String getAccountIds = "https://lilbill.herokuapp.com/user/";


        //Sækja lista yfir öll account ID fyrir þennann user
        //Kalla á GET með user Id
        //fyrir all account Id's
        //Get - / user / {userId} / accounts

        try {
            mAccountIds = get.getAccounts(getAccountIds + mUser.getId() + "/accounts");
            //Log.i(TAG, "" + username);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //nota for lykkju og kalla á getAccountData fyrir hvert ID
        // Get - / user / {userID} / account / {accountId}
        String account = "https://lilbill.herokuapp.com/user/";
        mAccounts = new ArrayList<>();


        for(int i = 0; i < mAccountIds.size(); i++){
            try {
                Account a = get.getAccountData(account + mUser.getId() + "/account/" + mAccountIds.get(i), mUser.getId());
                Log.i("account:", a.toString());
                mAccounts.add(a);
                mTransactions = a.getTransactionsList();
                //Log.i(TAG, "" + response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}

