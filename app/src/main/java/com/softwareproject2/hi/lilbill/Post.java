package com.softwareproject2.hi.lilbill;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

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
         * Aðferð sem framkvæmir post request til að bæta við í friendslist í user object
         * Skilar response og er unnið úr því annarstaðar.
         */
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String url = "https://lilbill.herokuapp.com/user/" + userId + "/add/"+ friendName;
        final String accountId;

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();

            String json = "{}";

            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String r = response.body().string();
                Log.i(TAG, r);

               return r;
            }
        }
        return null;

    }

    public String postJsonFromTransaction(Transaction transaction, String userId) throws IOException {
        /**
         * Þessi aðferð á að breyta java object, í þessu tilfelli Transaction object
         * yfir í json streng.
         */
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String url = "http://lilbill.herokuapp.com/user/" + userId + "/transaction/new";


        //Breytum transaction yfir í json
        Gson gson = new Gson();
        String json = gson.toJson(transaction);

        Log.e("json-transaction", json);
        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON,json);

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String r = response.body().string();
                Log.i(TAG, r);
                return r;
            }
        }
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

        Log.i(TAG, ""+ transactionList);
        Log.i(TAG, "" + json);

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
//                Log.i(TAG, response.body().string());
                return response.body().string();
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


