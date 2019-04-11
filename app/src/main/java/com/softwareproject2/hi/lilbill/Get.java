package com.softwareproject2.hi.lilbill;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

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

import static com.softwareproject2.hi.lilbill.TransactionActivity.TAG;

public class Get {

    Context mContext;

    public Get(Context mContext) {
        this.mContext = mContext;
    }


    public User getUserData(String url) throws IOException {

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

                Log.d(TAG, "line 62" + jsonData);

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
                    Log.i(TAG, mFriendsArray[i]);
                }
                User user = new User();
                user.setId(muserId);
                user.setEmail(mEmail);
                user.setFirstName(mFirstname);
                user.setLastName(mLastname);
                user.setUsername(mUsername);
                user.setFriends(mFriends);

                Log.i(TAG, user.toString());
                return user;

            } catch (IOException e) {

            }
        }

        return null;

    }

    public List<String> getAccounts(String url) throws IOException {

        if(isNetworkAvailable()) {
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

                Log.d(TAG, "account Id list" + jsonData);

                JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

                final JsonArray accountList = jsonObject.get("accounts").getAsJsonArray();
                final String[] accounts = new String[accountList.size()];
                final List<String> accountIds = new ArrayList<>();

                for(int i = 0; i < accountList.size(); i++) {
                    accountIds.add(accountList.get(i).toString());
                }

                return  accountIds;

            } catch (IOException e) {

            }
        }
        return null;
    }

    public Transaction getTransaction(String url) throws IOException {

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

//                Log.d(TAG, "line 62" + jsonData);

                JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

                final String mId = jsonObject.get("id").getAsString();
                final String mAccountId = jsonObject.get("accountId").getAsString();
                final String mAmountString = jsonObject.get("amount").getAsString();
                final Float mAmount = Float.parseFloat(mAmountString);
                //final String mDescr = jsonObject.get("descr").getAsString();
                final String mDescr = "fokk you";
                final String mDate = jsonObject.get("date").getAsString();

                Transaction transaction = new Transaction();
                transaction.setId(mId);
                transaction.setAccountId(mAccountId);
                transaction.setAmount(mAmount);
                transaction.setDescription(mDescr);
                transaction.setDate(mDate);


                return transaction;

            } catch (IOException e) {

            }
        }
        return null;
    }

    public Account getAccountData(String url, String userId) throws IOException {

        /* Strengur sem er url sem samsvarar hvert á að sækja gögn
           urlið væri root/user/json eða eh álíka, þar sem user er sá sem er logged in
           og json er þá json viewið.
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

                Log.d(TAG, "line 62" + jsonData);

                JsonObject jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();

                final String mUser2 = jsonObject.get("user2").getAsString();
                final String mUser1 = jsonObject.get("user1").getAsString();
                final String mAccountId = jsonObject.get("id").getAsString();
                final Float mNetBalance = jsonObject.get("netBalance").getAsFloat();

                final JsonArray transactionsList = jsonObject.get("transactionList").getAsJsonArray();
                //final String[] transactions = new String[transactionsList.size()];
                final List<Transaction> mTransactionList = new ArrayList<>();

                //Get - / user / {userId} / transaction / {transactionId}

                for(int i = 0; i < transactionsList.size(); i++) {
                    String transUrl = "https://lilbill.herokuapp.com/user/" + userId + "/transaction/" + transactionsList.get(i);

                    mTransactionList.add(getTransaction(transUrl));
                }

                Account account = new Account();
                account.setId(mAccountId);
                account.setUser1(mUser1);
                account.setUser2(mUser2);
                account.setNetBalance(mNetBalance);
                account.setTransactionsList(mTransactionList);

                Log.i(TAG, account.toString());
                return account;

            } catch (IOException e) {

            }
        }

        return null;
    }

    private boolean isNetworkAvailable() {
        /**
         * Fall sem athugar að allt sé í lagi með network manager.
         */
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) isAvailable = true;
        return isAvailable;
    }

}
