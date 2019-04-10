package com.softwareproject2.hi.lilbill;


import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.softwareproject2.hi.lilbill.features.account.Account;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;
import com.softwareproject2.hi.lilbill.features.transaction.TransactionConstructionActivity;
import com.softwareproject2.hi.lilbill.features.transaction.TransactionFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MainActivity extends SingleFragmentActivity {


//    NetworkManager networkManager = new NetworkManager(this);

    public static final String TAG = MainActivity.class.getSimpleName();

    public static final String EXTRA_TRANSACTION_ID =
            "com.softwareproject2.hi.lilbill.transaction_id";


    public static Intent newIntent(Context packageContext, UUID transactionID, UUID accountId) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_TRANSACTION_ID, transactionID);
        return intent;
    }

    protected Fragment createFragment() {
        return new TransactionFragment();

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        /*
        Post post = new Post(this);
        Get get = new Get(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        String url1 = "https://lilbill.herokuapp.com/user/";
        String url2 = "https://postman-echo.com/post";
        Log.i(TAG, url2);

        try {
          Account account = get.getAccountData(url1);
          String user1 = account.getUser1();
          Log.i(TAG, "" + user1);
       } catch (IOException e) {
             e.printStackTrace();
        }

        /*
        Transaction transaction = new Transaction();
        transaction.setDescription("dsa");

        final List<Transaction> mTransactionList = new ArrayList<>();
        mTransactionList.add(transaction);
        Account account = new Account();
        account.setUser1("user1");
        account.setUser2("user2");

        account.setTransactionsList(mTransactionList);



        try {
            String response = post.postJsonFromAccount(account, url2);
            Log.i(TAG, "" + response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        */

        FloatingActionButton createNewTransaction = findViewById(R.id.new_transaction_fab);
        createNewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TransactionConstructionActivity.class));
            }
        });
    }
}