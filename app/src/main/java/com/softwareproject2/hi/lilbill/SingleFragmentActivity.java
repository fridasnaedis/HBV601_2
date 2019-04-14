package com.softwareproject2.hi.lilbill;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.softwareproject2.hi.lilbill.features.transaction.NewFriendActivity;
import com.softwareproject2.hi.lilbill.features.transaction.Transaction;
import com.softwareproject2.hi.lilbill.features.transaction.TransactionConstructionActivity;
/**
 * SingleFragmentActivity implements an onCreate function that is reused in
 * AccountListActivity, TransactionListActivity and TransactionActivity
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FloatingActionButton createNewTransaction = findViewById(R.id.new_transaction_fab);
        createNewTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SingleFragmentActivity.this, TransactionConstructionActivity.class));
            }
        });

        //Create and commit a fragment transaction
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            //creates and returns an instance of FragmentTransaction
            //Then we can chain fragments together
            fm.beginTransaction()
                    //(container view id, fragment)
                    //container view id tells where the fragments view should appear - unique identifyer
                    //this is the resource ID of the container view ID
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
