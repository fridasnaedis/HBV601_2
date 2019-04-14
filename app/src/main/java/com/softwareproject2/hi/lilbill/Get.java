package com.softwareproject2.hi.lilbill;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.features.account.User;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Get {

    Context mContext;

    public Get(Context mContext) {
        this.mContext = mContext;
    }


    public User getUserData(String url) throws IOException {
        /**
         * Methods which takes an URL as a parameter and perfomrs a Get request to fetch
         * User data
         * Returns a User object
         */
        if (isNetworkAvailable()) {
            // Check if network is available
            try {
                // Instanciate client
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response responses = null;

                try {
                    // Do Get request
                    responses = client.newCall(request).execute();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Convert response into String
                String jsonData = responses.body().string();
                if (jsonData.contains("null") || jsonData.contains("error")) {
                    return null;
                } else {

                    // Fetch Data from jsonObject  ----------------------------------
                    JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

                    final String mUsername = jsonObject.get("username").getAsString();
                    final String mFirstname = jsonObject.get("firstname").getAsString();
                    final String mLastname = jsonObject.get("lastname").getAsString();
                    final String mEmail = jsonObject.get("email").getAsString();
                    final String muserId = jsonObject.get("id").getAsString();

                    final JsonArray jsonArray = jsonObject.get("friendlist").getAsJsonArray();
                    final String[] mFriendsArray = new String[jsonArray.size()];
                    final List<String> mFriends = new ArrayList<>();

                    for (int i = 0; i < jsonArray.size(); i++) {
                        mFriendsArray[i] = jsonArray.get(i).getAsString();
                    }

                    for (int i = 0; i < mFriendsArray.length; i++) {
                        mFriends.add(mFriendsArray[i]);
                    }

                    // Populate fields in new User object
                    User user = new User();
                    user.setId(muserId);
                    user.setEmail(mEmail);
                    user.setFirstName(mFirstname);
                    user.setLastName(mLastname);
                    user.setUsername(mUsername);
                    user.setFriends(mFriends);

                    return user;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Return null if no network available
        return null;
    }

    public List<String> getAccounts(String url) throws IOException {
        /**
         * Method to perform Get request to get a list of account IDs for the signed in user
         * returns a List of AccountId's
         */

        if (isNetworkAvailable()) {
            // Check if network is available
            try {
                // Instanciate client
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String jsonData = responses.body().string();

                JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

                final JsonArray accountList = jsonObject.get("accounts").getAsJsonArray();
                final String[] accounts = new String[accountList.size()];
                final List<String> accountIds = new ArrayList<>();

                for (int i = 0; i < accountList.size(); i++) {
                    accountIds.add(accountList.get(i).toString());
                }

                return accountIds;

            } catch (IOException e) {

            }
        }
        return null;
    }

    public Transaction getTransaction(String url) throws IOException {
        /**
         * Uses a String URL to Perform a Get request to fetch data on a Transaction
         * Returns a Transaction object
         */

        if (isNetworkAvailable()) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String jsonData = responses.body().string();

                JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

                // Fetch data from jsonObject
                final String mAmmount = jsonObject.get("amount").getAsString();
                final String mId = jsonObject.get("id").getAsString();
                final String mDescr = jsonObject.get("descr").getAsString();
                final String mDate = jsonObject.get("date").getAsString();

                // Populate fields in a new Transaction Object
                Transaction transaction = new Transaction();
                transaction.setDate(mDate);
                transaction.setId(mId);
                transaction.setDescription(mDescr);
                transaction.setAmount(Float.valueOf(mAmmount));

                return transaction;

            } catch (IOException e) {

            }
        }
        // Return null if no network available
        return null;
    }

    public Account getAccountData(String url, String userId) throws IOException {

        /**
         * Method that has a String Url and a String UserId to fetch data on an account
         * that the user owns with a Get request.
         * Returns an Account object
         */

        if (isNetworkAvailable()) {
            // Establish connection
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String jsonData = responses.body().string();

                JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

                final String mUser2 = jsonObject.get("user2").getAsString();
                final String mUser1 = jsonObject.get("user1").getAsString();
                final String mAccountId = jsonObject.get("id").getAsString();
                final Float mNetBalance = jsonObject.get("netBalance").getAsFloat();

                final JsonArray transactionsList = jsonObject.get("transactionList").getAsJsonArray();
                final List<Transaction> mTransactionList = new ArrayList<>();

                for (int i = 0; i < transactionsList.size(); i++) {
                    String transUrl = "https://lilbill.herokuapp.com/user/" + userId + "/transaction/" + transactionsList.get(i);

                    mTransactionList.add(getTransaction(transUrl));
                }
                // Populate a new Account object with data from Json Object

                Account account = new Account();
                account.setId(mAccountId);
                account.setUser1(mUser1);
                account.setUser2(mUser2);
                account.setNetBalance(mNetBalance);
                account.setTransactionsList(mTransactionList);

                return account;

            } catch (IOException e) {

            }
        }

        return null;
    }

    private boolean isNetworkAvailable() {
        /**
         * A method which checks if network is available.
         */
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) isAvailable = true;
        return isAvailable;
    }

}
