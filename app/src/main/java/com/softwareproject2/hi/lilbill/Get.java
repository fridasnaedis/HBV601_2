package com.softwareproject2.hi.lilbill;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.softwareproject2.hi.lilbill.features.account.Account;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.softwareproject2.hi.lilbill.MainActivity.TAG;

public class Get {

    Context mContext;

    public Get(Context mContext) {
        this.mContext = mContext;
    }

    public Account getAccountData(String url) throws IOException {

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

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                JsonParser parser = new JsonParser();
                Account account = gson.fromJson(jsonData, Account.class);
                Log.i(TAG, String.valueOf(account));
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
