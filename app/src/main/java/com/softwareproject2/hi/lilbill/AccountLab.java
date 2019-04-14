package com.softwareproject2.hi.lilbill;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.features.account.User;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AccountLab {

    private static AccountLab sAccountLab;
    private List<Transaction> mTransactions;
    private List<String> mAccountIds;
    private List<Account> mAccounts;
    private User mUser;
    private Get get;
    private Post post;

    public static AccountLab get(Context context) {
        if (sAccountLab == null) {
            sAccountLab = new AccountLab(context);
        }
        return sAccountLab;
    }

    /**
     * Constuctor
     * @param context
     */
    private AccountLab(Context context) {

        //Create instances of the get and post classes
        get = new Get(context);
        post = new Post(context);

        //Permit threads on main thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    /**
     * Get all accounts
     * @return
     */
    public List<Account> getAccounts() {
        return mAccounts;
    }

    /**
     * Get a single transaction from Id
     * @param id
     * @return
     */
    public Transaction getTransaction(String id) {
        for (Transaction transaction : mTransactions) {
            if (transaction.getId().equals(id)) {
                return transaction;
            }
        }
        return null;
    }


    /**
     * Get a single account from Id
     * @param id
     * @return
     */
    public Account getAccount(String id) {
        for (Account account : mAccounts) {
            if (account.getId().equals(id)) {

                //Set mTransactions for the current account we are viewing
                mTransactions = account.getTransactionsList();
                return account;
            }
        }
        return null;
    }

    /**
     * Get the logged in user
     * @return
     */
    public User getUser() {
        return mUser;
    }

    /**
     * Create a new Transaction by calling a method in post
     * @param t
     * @param id
     */
    public void createTransaction(Transaction t, String id) {

        final String response;
        final String transactionId;
        final String date;

        //Call the post request and update the date and Id from the response
        try {
            response = post.postJsonFromTransaction(t, mUser.getId());
            JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
            transactionId = jsonObject.get("id").getAsString();
            date = jsonObject.get("date").getAsString();
            t.setId(transactionId);
            t.setDate(date);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Get the account and update the net balance
        Account account = getAccount(id);
        account.setNetBalance(account.getNetBalance()+t.getAmount());

        //Finally add the transaction to the account and update the current transactions
        account.addTransaction(t);
        mTransactions = account.getTransactionsList();
    }

    /**
     * Add a new friend and create the corresponding Account entity
     * @param userId
     * @param friendUserName
     * @return
     */
    public String addFriend(String userId, String friendUserName){


        try {
            String response =  post.postJsonFromAddFriend(userId, friendUserName);
            String error1 = "no user with username:";
            String error2 = "user with username:";

            //!Crude error handling!
            if(!response.contains(error1) && !response.contains(error2)) {

                JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();

                //Response returns information for the account
                Account a = new Account();
                final String mUser2 = jsonObject.get("user2").getAsString();
                final String mUser1 = jsonObject.get("user1").getAsString();
                final String mAccountId = jsonObject.get("id").getAsString();

                a.setId(mAccountId);
                a.setUser1(mUser1);
                a.setUser2(mUser2);

                //Add the account to the current accounts
                mAccounts.add(a);
            }
            return response;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Fake login that only checks the username
     * @param username
     * @param password
     * @param context
     */
    public String logIn(String username, String password, Context context) {

        String login = "https://lilbill.herokuapp.com/login/";
        String response = "";

        //Get the user by username
        try {
        mUser = get.getUserData(login + username);
        } catch (IOException e){
            e.printStackTrace();
        }

        if(mUser == null){
            response = "null";
            return response;

        } else {
            String getAccountIds = "https://lilbill.herokuapp.com/user/";
            //Send a get request for all account Id's for that user
            try {
                mAccountIds = get.getAccounts(getAccountIds + mUser.getId() + "/accounts");
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Finally get all the accounts by Id
            String getAccount = "https://lilbill.herokuapp.com/user/";

            mAccounts = new ArrayList<>();
            for (int i = 0; i < mAccountIds.size(); i++) {
                try {
                    Account a = get.getAccountData(getAccount + mUser.getId() + "/account/" + mAccountIds.get(i), mUser.getId());
                    mAccounts.add(a);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }
}

