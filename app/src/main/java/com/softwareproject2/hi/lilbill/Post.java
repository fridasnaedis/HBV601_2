package com.softwareproject2.hi.lilbill;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.softwareproject2.hi.lilbill.features.transaction.TransactionActivity.TAG;

public class Post {

    Context mContext;

    public Post(Context mContext) {
        this.mContext = mContext;
    }


    public String postJsonFromAddFriend(String userId, String friendName) throws IOException {
        /**
         * Method which performs a post request to add a friend to a users friendslist
         * Returns a response which is processed elsewhere.
         */
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        // Create url for post request
        String url = "https://lilbill.herokuapp.com/user/" + userId + "/add/"+ friendName;

        // Check if network is available.
        if (isNetworkAvailable()) {
            // Instanciate the client
            OkHttpClient client = new OkHttpClient();

            String json = "{}";

            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            // Try to perform post request
            try (Response response = client.newCall(request).execute()) {
                String r = response.body().string();

                // return response
               return r;
            }
        }
        // Return null if no network
        return null;

    }

    public String postJsonFromTransaction(Transaction transaction, String userId) throws IOException {
        /**
         * Method which transforms a Transaction object to a json String and performs a
         * Post request to make a new Transaction
         */
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String url = "http://lilbill.herokuapp.com/user/" + userId + "/transaction/new";


        //Turn Transaction object into a json String
        Gson gson = new Gson();
        String json = gson.toJson(transaction);

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON,json);

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            // Try to perform Post request
            try (Response response = client.newCall(request).execute()) {
                String r = response.body().string();

                // return response
                return r;
            }
        }
        // Return null if no network
        return null;
    }


    public String postJsonFromAccount(Account account, String url) throws IOException {
        /**
         * Þessi aðferð á að breyta java object, í þessu tilfelli Account object
         * yfir í json streng.
         */
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        final List<Transaction> mTransactionList = new ArrayList<>();
        String[] transactionList = new String[mTransactionList.size()];
        for (int i = 0; i < transactionList.length; i++) {
            transactionList[i] = mTransactionList.get(i).getId().toString();
        }


        String json = "{'Id':" + account.getId() + ","
                + "'transactionList':[" + transactionList + "],"
                + "'user1': " + account.getUser1() + ","
                + "'user2': " + account.getUser2() + ","
                + "'netBalance': " + account.getNetBalance() + "}";


        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        }
        return null;
    }


    private boolean isNetworkAvailable() {
        /**
         * Method that checks if network is available.
         */
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) isAvailable = true;
        return isAvailable;
    }
}


