package com.softwareproject2.hi.lilbill.features.account;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.softwareproject2.hi.lilbill.Get;
import com.softwareproject2.hi.lilbill.Post;
import com.softwareproject2.hi.lilbill.R;
import com.softwareproject2.hi.lilbill.SingleFragmentActivity;

import java.io.IOException;

public class AccountListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AccountListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //Post post = new Post(this);
       /* Get get = new Get(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        //Fá EXTRA frá login activity með username og parsa við url1

        String url1 = "https://lilbill.herokuapp.com/user/";
        String url2 = "https://postman-echo.com/post";
        //Log.i(TAG, url2);

        try {
            User user = get.getUserData(url1+2);
            String username = user.getUsername();
            //Log.i(TAG, "" + username);
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
    }
}

