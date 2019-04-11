package com.softwareproject2.hi.lilbill;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.LogPrinter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.features.account.User;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.softwareproject2.hi.lilbill.TransactionActivity.TAG;

public class Post {

    Context mContext;

    public Post(Context mContext) {
        this.mContext = mContext;
    }

    public String postJsonFromAddFriend(String userId, String friendName) throws IOException {
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String url = "https://lilbill.herokuapp.com/user/" + userId + "/add/"+ friendName;

        if (isNetworkAvailable()) {

            OkHttpClient client = new OkHttpClient();

            String json = "{}";

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

        /*
        Post - / user / {userId} / add / {friendName}
        example: / user / 5 / add / isak

        body:
        response:
        {
            "id": 6,
                "transactionList": [],
            "user1": "test",
                "user2": "isak",
                "netBalance": 0
        }*/
    }

    public String postJsonFromTransaction(Transaction transaction, String url) throws  IOException {
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        /*
        Post - / user / {userId} / transaction / new
                example: / user / 5 / transaction / new

                body:
        {
            "accountId": "6",
                "amount": "500",
                "descr": "test ---- "
        }
        response:
        {
            "id": 6,
                "accountId": 6,
                "amount": 500,
                "descr": "test ---- ",
                "date": "Apr 7, 2019 9:33:54 PM"
        }

        String json = "{
        */
        return null;
    }

    public String postJsonFromAccount(Account account, String url) throws IOException {
        /**
         * Þessi aðferð á að breyta java object, í þessu tilfelli Account object
         * yfir í json streng.
         */
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

//        GsonBuilder builder = new GsonBuilder();
//
//        Gson gson = builder.create();
//        JsonElement jsonElement = gson.toJsonTree(acccount);
//        JsonObject jsonObject = (JsonObject) jsonElement;
//        String json = jsonObject.toString();
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


